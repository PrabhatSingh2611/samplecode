package ch.windmill.audra.graphql.type;

import java.util.UUID;

import lombok.Data;

@Data
public class UserPosition {
	private UUID id;
	private String name;
}
