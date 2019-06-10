package filippos.addimando.xechallengerestapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import model.APIResponseClassified;
import model.Classified;
import service.ClassifiedsService;

@RunWith(SpringRunner.class)
public class ClassifiedsServiceTest {
	
	private Classified testClassified1 = new Classified();
	private Classified testClassified2 = new Classified();
	private Classified testClassified3 = new Classified();
	private Classified testClassified4 = new Classified();
	private Classified testClassified5 = new Classified();
	private Classified testClassified6 = new Classified();
	
    private ClassifiedsService classifiedsService = new ClassifiedsService();
    
    @Before 
    public void prepareTest() {
    	testClassified1.setText("﻿Μεσιτικη εταιρεια στα "
        		+ "πλαισια της διαρκους αναπτυξης της ζητει \"Assistant Manager\", προσφεροντας ενα "
        		+ "ιδιαιτερα ελκυστικο πακετο αμοιβων, μεγαλες προοπτικες περαιτερω εξελιξης. "
        		+ "Ο ιδανικος υποψηφιος/α, θα πρεπει να διαθετει: εμπειρια στον κλαδο των ακινητων ή της "
        		+ "διαχειρισης ανθρωπινου δυναμικου, οργανωτικο πνευμα, και διαπραγματευτικες ικανοτητες "
        		+ "Τηλ επικοινωνιας 6999999999(153) 400€");
    	
    	testClassified2.setText("﻿ΖΗΤΟΥΝΤΑΙ υπάλληλοι σε καταστήματα πώλησης παραδοσιακών προϊόντων και "
    			+ "καλλυντικών για την περίοδο Ιουνίου έως και Σεπτεμβρίου (4 Μήνες). Απαραίτητη γνώση της "
    			+ "Αγγλικής γλώσσας, γνώση ρωσικής γλώσσας θα εκτιμηθεί. Διαμονή πληρωμένη σε διαμέρισμα κοντά "
    			+ "στο χώρο εργασίας. Μισθός 800 ευρώ. email: info@here.gr");
    	
    	testClassified3.setText("ΚΥΨΕΛΗ διαμέρισμα 60 τ.μ., 1ου, προσόψεως, διαμπερές, 1 υ/δ, μπάνιο, ξεχωριστή "
    			+ "κουζίνα, κλιματισμός, πόρτα ασφαλείας, ελεύθερο, ανακαινισμένο, εξαιρετικό πλήρως ανακαινισμένο "
    			+ "διαμέρισμα σε ήσυχη περιοχή, αποτελείται από ένα φωτεινότατο σαλόνι, ξεχωριστή ανακαινισμένη "
    			+ "κουζίνα, ανακαινισμένο μπάνιο. Διπλά τζάμια, ανακαινισμένη κουζίνα, μπάνιο, πατώματα. "
    			+ "Ανακαινισμένα ηλεκτρικά και υδραυλικά. Η ανακαίνιση έγινε το 2019. Μόλις 4 λεπτά από τα "
    			+ "δικαστήρια στην Ευελπίδων. Κατάλληλο και για επαγγελματική χρήση.");
    	
    	testClassified4.setText("﻿ΚΥΒΕΡΝΗΤΗΣ Γ' πιστοποιημένος ζητείται για ημερόπλοιο μοντέλου Πέραμα στη Μύκονο "
    			+ "there@gmail.com");
    	
    	testClassified5.setText("﻿ABARTH S4 595FL, 1.400 cc, μοντ. 6/'17, 160 hp, μαύρο μεταλλικό, 4.000 χλμ., 3θυρο, "
    			+ "Τ-Jet Pista 1368, 10 φορολογίσιμοι ίπποι, τιμή 10.000€");
    	
    	testClassified6.setText("﻿ΑΝΑΒΥΣΣΟΣ μεζονέτα 195 τ.μ., ισόγεια, 3 υ/δ, κατασκευή '12, 2 μπάνια, σε οικόπεδο "
    			+ "375 τ.μ., αυτόνομη θέρμανση, κλιματισμός, τζάκι, θέα θάλασσα, κλειστό πάρκιν, αποθήκη, κήπος, "
    			+ "άριστη κατάσταση, 2 κυρίως επιπέδων, ευήλιο καθιστικό με θέα, υπόγειο φωτεινό που χρησιμοποιείται "
    			+ "ως πλήρης ξενώνας, πισίνα με εξοπλισμένο περιβάλλοντα χώρο χαλάρωσης, walking distance από οργανωμένη "
    			+ "παραλία, τιμή 570.000€");
    }
        
    //Testing the Classifieds Service - we should get the expected word count in every case.
    @Test
    public void getCorrectWordCounts() {
    	
    	APIResponseClassified result1 = classifiedsService.getClassifiedWithWordCount(testClassified1, false);
    	APIResponseClassified result2 = classifiedsService.getClassifiedWithWordCount(testClassified2, false);
    	APIResponseClassified result3 = classifiedsService.getClassifiedWithWordCount(testClassified3, false);
    	APIResponseClassified result4 = classifiedsService.getClassifiedWithWordCount(testClassified4, false);
    	APIResponseClassified result5 = classifiedsService.getClassifiedWithWordCount(testClassified5, false);
    	APIResponseClassified result6 = classifiedsService.getClassifiedWithWordCount(testClassified6, false);

    	Assert.assertEquals(result1.getWords(), 45);
    	Assert.assertEquals(result2.getWords(), 41);
    	Assert.assertEquals(result3.getWords(), 59);
    	Assert.assertEquals(result4.getWords(), 11);
    	Assert.assertEquals(result5.getWords(), 22);
    	Assert.assertEquals(result6.getWords(), 51);

    }
 
}