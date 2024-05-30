package com.rainhard.notes.config;

import com.rainhard.notes.entity.Notes;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.time.LocalDateTime;

@Configuration
public class AppListener {

    @Bean
    public ApplicationListener<BeforeSaveEvent> automateNotesCreatedDate(){
        return new ApplicationListener<BeforeSaveEvent>() {
            @Override
            public void onApplicationEvent(BeforeSaveEvent event) {
                var entity = event.getEntity();
//                if(entity instanceof Notes){
//                    ((Notes) entity).setCreatedAt(LocalDateTime.now());
//                }
            }
        };
    }
}
