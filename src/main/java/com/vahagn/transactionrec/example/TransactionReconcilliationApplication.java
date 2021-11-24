package com.vahagn.transactionrec.example;

import com.vahagn.transactionrec.example.handler.FinalHandler;
import com.vahagn.transactionrec.example.handler.IdenticalTransactionsHandler;
import com.vahagn.transactionrec.example.handler.SimilarTransactionsHandler;
import com.vahagn.transactionrec.example.handler.TransactionsHandler;
import com.vahagn.transactionrec.example.service.FileService;
import com.vahagn.transactionrec.example.service.MultiPartFileService;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;


/**
 * The type Transaction reconcilliation application.
 */
@SpringBootApplication
public class TransactionReconcilliationApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TransactionReconcilliationApplication.class, args);
    }

    @Value("${file.dir}")
    private String fileDir;

    /**
     * Storage service file service.
     *
     * @return the file service
     */
    @Bean
    public FileService storageService() {
        return new MultiPartFileService(fileDir);
    }

    /**
     * Transactions handler transactions handler.
     *
     * @return the transactions handler
     */
    @Bean
    public TransactionsHandler transactionsHandler() {
        return new IdenticalTransactionsHandler(new SimilarTransactionsHandler(new FinalHandler()));
    }

    /**
     * Cookie processor customizer web server factory customizer.
     *
     * @return the web server factory customizer
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return tomcatServletWebServerFactory -> tomcatServletWebServerFactory.addContextCustomizers((TomcatContextCustomizer) context -> {
            context.setCookieProcessor(new LegacyCookieProcessor());
        });
    }

}
