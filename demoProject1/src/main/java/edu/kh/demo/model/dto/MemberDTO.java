package edu.kh.demo.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter+setter+toStirng
@NoArgsConstructor // 기본 생성자
public class MemberDTO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
}
