package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import model.Classified;
import model.APIResponseClassified;
import service.ClassifiedsService;

@CrossOrigin
@RestController
public class ClassifiedsController {

	@Autowired
	ClassifiedsService classifiedsService;

	@PostMapping(path = "/count", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
	public APIResponseClassified getWordCount(@RequestBody Classified classified) {
		return classifiedsService.getClassifiedWithWordCount(classified, false);
	}
	
	@PostMapping(path = "/countHtml", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
	public APIResponseClassified getWordCountAndStripHtml(@RequestBody Classified classified) {
		return classifiedsService.getClassifiedWithWordCount(classified, true);
	}

}