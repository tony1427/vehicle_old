package com.walnutvm.motorcycle.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BaseRepository<T>: JpaRepository<T, UUID> {
}