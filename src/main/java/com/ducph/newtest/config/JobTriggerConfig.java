package com.ducph.newtest.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
@RequiredArgsConstructor
public class JobTriggerConfig {

    private final JobDetail testJob;

    @Bean
    public Trigger triggerTestJob() {
        return this.intitializeJobTrigger("My Test", "0/3 * * * * ?", testJob);
    }

    private Trigger intitializeJobTrigger(String subject, String schedule, JobDetail jobDetail) {
        var jobTrigger = String.format("%s Job Trigger", subject);
        return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(jobTrigger.replace(StringUtils.SPACE, StringUtils.EMPTY))
                .withDescription(jobTrigger)
                .withSchedule(CronScheduleBuilder.cronSchedule(schedule).inTimeZone(TimeZone.getTimeZone("GMT+7")))
                .startNow()
                .build();
    }
}
