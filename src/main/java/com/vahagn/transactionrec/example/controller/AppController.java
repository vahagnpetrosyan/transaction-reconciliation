package com.vahagn.transactionrec.example.controller;

import com.vahagn.transactionrec.example.dto.ReconciliationResult;
import com.vahagn.transactionrec.example.exception.BadRequestException;
import com.vahagn.transactionrec.example.service.FileService;
import com.vahagn.transactionrec.example.service.ReconciliationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * The type App controller.
 */
@Controller
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);


    @Autowired
    private FileService fileService;

    @Autowired
    private ReconciliationService reconciliationService;

    /**
     * Home string.
     *
     * @return the string
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    /**
     * Reconcile model and view.
     *
     * @param file1 the file 1
     * @param file2 the file 2
     * @return the model and view
     */
    @PostMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView reconcile(@RequestParam(value = "file1") MultipartFile file1,
                                  @RequestParam(value= "file2") MultipartFile file2) {
        logger.info(" a request to reconcile {} with {} .", file1.getOriginalFilename(), file2.getOriginalFilename());

        Map<String, Object> modelMap = new HashMap<>();

        fileService.store(file1);
        fileService.store(file2);


        try {
            reconciliationService.reconcile(file1.getOriginalFilename(), file2.getOriginalFilename());
        } catch (IllegalArgumentException e) {
            logger.warn("Exception occurred during transaction reconciliation.", e);
            throw new BadRequestException(e.getMessage(), e);
        }
        finally {
            fileService.delete(file1.getOriginalFilename());
            fileService.delete(file2.getOriginalFilename());
        }
        modelMap.put("file1_name", file1.getOriginalFilename());
        modelMap.put("file2_name", file2.getOriginalFilename());
        modelMap.put("reconciliation_result", ReconciliationResult.getInstance());
        return new ModelAndView("result :: reconciliation-fragment", "model", modelMap);
    }


}
