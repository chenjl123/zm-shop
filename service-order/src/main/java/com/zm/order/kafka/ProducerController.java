package com.zm.order.kafka;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/kafka")
@Slf4j
public class ProducerController {
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
 
    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public void consume(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
    	JSONObject obj = new JSONObject();
        for (int i = 3; i<6; i++){
        obj.put("bar", "100"+i);
    	ListenableFuture<SendResult<String, String>> future  = kafkaTemplate.send("test008", obj.toString());
    	future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
    		 
             @Override
             public void onSuccess(SendResult<String, String> result) {
            	 System.out.println("send success {}"+result.getProducerRecord().value());
                 log.info("send success {}", result.getProducerRecord().value());
             }
  
             @Override
             public void onFailure(Throwable ex) {
            	 System.out.println("send fail {}"+ex.getMessage());
            	 //加入日志表，扫描日志表重新发送，防止消息丢失
                 log.warn("send fail {}", ex.getMessage());
             }
  
         });
    	 Thread.sleep(2000);
       }
    }
    
    @RequestMapping(value = "/goods_minus", method = RequestMethod.GET)
    public void goods_minus(){
    	kafkaTemplate.send("goods_minus", "{库存数量减一}");
    }
}
