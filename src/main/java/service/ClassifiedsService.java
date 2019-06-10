package service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import model.Classified;
import model.APIResponseClassified;

//Using JSoup for stripping HTML
import org.jsoup.*;
import org.jsoup.safety.Whitelist;

@Service
public class ClassifiedsService {
	
	//Some keywords that may be used to signal price inside a classified
	private static List<String> priceKeyWords = Arrays.asList("€", "ευρώ", "ευρω", "eur", "ΕΥΡΩ", "euro", "euros");
	
	//Regex pattern to extract numerical values from string
	private static Pattern p = Pattern.compile("\\d+");

	public ClassifiedsService() {}

	public APIResponseClassified getClassifiedWithWordCount(Classified classified, boolean stripHtml) {
		
		APIResponseClassified apiResponseClassified = new APIResponseClassified();
		
		//We want to return the whole text of the classified in the response.
		if (stripHtml) {
			apiResponseClassified.setText(extractTextWithoutHTML(classified.getText()));
		} else {
			apiResponseClassified.setText(classified.getText());
		}
		
		//Split text by space characters
		String[] splittedWordsBySpace = getWordsSplitBySpace(classified.getText());
		
		//Count the words.
		apiResponseClassified.setWords(getCount(splittedWordsBySpace));
		
		//Get the price in Euros if applicable.
		apiResponseClassified.setPrice(getPrice(splittedWordsBySpace));
		
		return apiResponseClassified;
	}
	
	/**
	 * Accepts a text and splits it by spaces, then returns the resulting array of strings.
	 */
	private String[] getWordsSplitBySpace(String text) {
		String[] splitted = text.split("\\s+");
		return splitted;
	}
	
	/**
	 * Counts the word count - Only words with two or more characters count as one word.
	 */
	private int getCount(String[] words) {
		int count = 0;
		for (String word: words) {
			if (word.length() > 1) count++;
		}
		return count;
	}
	
	/**
	 * Extracts a numerical value from a string, according to a regex Pattern.
	 */
	private String extractNumericalValueFromWord(String word) {
		 Matcher m = p.matcher(word);
		 StringBuilder sb = new StringBuilder();
		 while (m.find()) {
			 sb.append(m.group());
		 }
		 return sb.toString();
	}
	
	/**
	 * Strips a classified from any HTML, and returns the plain text.
	 */
	private String extractTextWithoutHTML(String text) {
		return Jsoup.clean(text, Whitelist.none());
	}
	
	/**
	 * Reads the price in Euros (€) from the text of a classified, if applicable.
	 */
	private String getPrice(String[] words) {
		
		Double price = null;
		String returnValue;
		
		//If we find the "€" character somewhere in the text, 
		//check word before or after character to determine price.
		for (int i = 0; i < words.length; i++) {
			
			//Check against price keywords to find if there is information about the price inside the classified's text
			boolean priceInformationFound = false;
			
			for(String priceKeyWord: priceKeyWords) {
				if (words[i].contains(priceKeyWord)) {
					priceInformationFound = true;
				}
			}

			//We have a word in the classified that contains information about price, try to extract the value
			if (priceInformationFound) {
				
				//First check to see if the price is included in the same word as the one containing the keyword.
				if (extractNumericalValueFromWord(words[i]) != null && 
				    !extractNumericalValueFromWord(words[i]).isEmpty()) 
				{
					String extractedPrice = extractNumericalValueFromWord(words[i]);
					price = Double.parseDouble(extractedPrice);				}
				
				//Check the previous word.
				else if (extractNumericalValueFromWord(words[i - 1]) != null && 
						!extractNumericalValueFromWord(words[i - 1]).isEmpty()) 
				{
					String extractedPrice = extractNumericalValueFromWord(words[i - 1]);
					price = Double.parseDouble(extractedPrice);
				}
				
				//Check the next word.
				else if (extractNumericalValueFromWord(words[i + 1]) != null && 
						!extractNumericalValueFromWord(words[i + 1]).isEmpty()) 
				{
					String extractedPrice = extractNumericalValueFromWord(words[i + 1]);
					price = Double.parseDouble(extractedPrice);				
				}
				
			}
		}
		return (price == null) ? "N/A" : (price.toString() + " €");
	}

} 