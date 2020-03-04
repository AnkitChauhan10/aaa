package com.trs.cc.news.model;

import com.trs.cc.news.utils.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("notification_apis")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SponsorAPI extends PathTrail {

	@Id
	String id;
	
	String name;
	
	String description;

	List<Roles> roles;
	
	
}
