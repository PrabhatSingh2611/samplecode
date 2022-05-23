package api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryVariables {
	private String uuid;
	private String title;
	private String icon;
	private String name;
	private String email;
	private String password;
}
