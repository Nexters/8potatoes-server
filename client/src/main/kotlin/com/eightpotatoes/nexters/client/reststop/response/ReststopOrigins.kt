package com.eightpotatoes.nexters.client.reststop.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class ReststopOrigins(val body: ReststopOriginBody)
