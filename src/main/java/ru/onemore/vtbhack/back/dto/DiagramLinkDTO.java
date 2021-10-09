package ru.onemore.vtbhack.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagramLinkDTO {
	private String id;
	private String start_id;
	private String start_port;
	private String end_id;
	private String end_port;

	public boolean equals(Object var1) {
		if (var1 == this) return true;

		if (getClass() != var1.getClass())
			return false;

		DiagramLinkDTO var1Final = (DiagramLinkDTO) var1;

		if(
			var1Final.getStart_id().equals(this.start_id) &&
			var1Final.getStart_port().equals(this.start_port) &&
			var1Final.getEnd_id().equals(this.end_id) &&
			var1Final.getEnd_port().equals(this.end_port)
		) {
			return true;
		}

		if(
			var1Final.getStart_id().equals(this.end_id) &&
			var1Final.getStart_port().equals(this.end_port) &&
			var1Final.getEnd_id().equals(this.start_id) &&
			var1Final.getEnd_port().equals(this.start_port)
		) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return start_id.length() + end_id.length();
	}
}
