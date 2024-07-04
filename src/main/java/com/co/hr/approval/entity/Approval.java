package com.co.hr.approval.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Approval {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approval_id", columnDefinition = "BIGINT")
    private Long id;
	
	private String appGroup;
	private String appName;
	private String appContent;
	private String appStartDate;
	private String appEndDate;
	private String appCheck1;
	private String appCheck2;
	private String useYn;
}
