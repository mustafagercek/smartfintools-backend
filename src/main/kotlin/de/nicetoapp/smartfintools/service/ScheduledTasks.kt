package de.nicetoapp.smartfintools.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTasks {

    @Scheduled(fixedRate = 5000)  // 600000 ms = 10 minutes
    fun reportCurrentTime() {
        println("The time now is ${System.currentTimeMillis()}")
    }
}