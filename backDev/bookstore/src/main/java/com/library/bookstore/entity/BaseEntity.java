package com.library.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;
}