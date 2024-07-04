package com.co.hr.staff.entity;

import com.co.hr.department.dto.DepartmentDto;
import com.co.hr.department.dto.TempDepartmentDto;

import com.co.hr.department.entity.Department;
import com.co.hr.department.entity.TempDepartment;
import com.co.hr.staff.dto.StaffDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Staff extends BaseEntity implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", columnDefinition = "BIGINT")
    private Long id;
	
	private String loginId;
	private String password;
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tempdepartment_id")
	private TempDepartment tempDepartment;

	private String position;
	private String email;
	
	@Embedded
	private Address address;
	
	private String useYn;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public void update(StaffDto dto) {
		if(dto.getId() != null) this.setId(dto.getId());
		if(dto.getLoginId() != null) this.setLoginId(dto.getLoginId());
		if(dto.getPassword() != null) this.setPassword(dto.getPassword());
		if(dto.getName() != null) this.setName(dto.getName());
		if(dto.getRoles() != null)this.setRoles(Collections.singletonList(dto.getRoles()));
		if(dto.getDepartment() != null)this.setDepartment(dto.getDepartment());
		if(dto.getTempDepartment() != null)this.setTempDepartment(dto.getTempDepartment());
		if(dto.getPosition() != null)this.setPosition(dto.getPosition());
		if(dto.getEmail() != null)this.setEmail(dto.getEmail());
		if(dto.getAddress() != null)this.setAddress(dto.getAddress());
	}
}
