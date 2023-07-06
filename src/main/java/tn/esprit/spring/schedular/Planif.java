package tn.esprit.spring.schedular;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.spring.services.IServices;

@Component
@AllArgsConstructor
@Slf4j
public class Planif {
    IServices iServices;

    @Scheduled(fixedRate = 60000)
    public void test(){
        log.info(iServices.afficher());
    }
}
