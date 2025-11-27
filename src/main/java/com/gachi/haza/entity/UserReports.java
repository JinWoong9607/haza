package com.gachi.haza.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_reports")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserReports {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", nullable = false)
    private Users reported;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "report_status", nullable = false)
    private String reportStatus;

}
