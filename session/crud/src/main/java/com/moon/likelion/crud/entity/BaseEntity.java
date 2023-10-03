package com.moon.likelion.crud.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.sound.sampled.AudioFileFormat;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AudioFileFormat.class)
@Getter
@Setter
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(updatable = true)
    private Instant getCreatedAt() {
        return createdAt;
    }
}
