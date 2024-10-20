package com.example.pr19

import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

data class Crime(val id: UUID = UUID.randomUUID(),var title:String,var isSloved:Boolean,val date:LocalDateTime)