package com.asadbaig.urlsqueeze.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="URL_INFO")
public class UrlInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_info_seq")
    @SequenceGenerator(name="url_info_seq", sequenceName = "url_info_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORIGINAL_URL")
    private String originalUrl;

    @Column(name = "SQUEEZED_URL")
    private String squeezedUrl;

    @Column(name = "URL_ID", nullable = false, unique = true)
    private String urlId;

    @Column(name = "IS_MODIFIABLE")
    private String isModifiable;

    @Column(name = "CREATED_TIME")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "MODIFIED_TIME")
    @UpdateTimestamp
    private LocalDateTime modifiedTime;

}
