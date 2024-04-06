package com.ducph.newtest.config;

import com.ducph.newtest.job.TestProcessJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobDetailConfig {

    @Bean
    public JobDetail testJob() {
        return this.initializeJobDetail("My Test", TestProcessJob.class);
    }

    private JobDetail initializeJobDetail(String subject, Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass).withIdentity(String.format("%s Job Detail", subject).replace(StringUtils.SPACE, StringUtils.EMPTY))
                .withDescription(String.format("%s Job Scheduler", subject))
                .storeDurably()
                .build();
    }
}
