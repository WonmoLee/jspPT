package test2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScPlayers {
	private int id;
	private String team;
	private int back_num;
	private String name;
	private String position;
}
