package com.walnutvm.motorcycle.repository

import com.walnutvm.motorcycle.entity.MotorcycleEntity
import org.springframework.stereotype.Repository

@Repository
interface MotorcycleRepository: BaseRepository<MotorcycleEntity> {
}