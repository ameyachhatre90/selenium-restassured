package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class represents the data structure for a Public Event request sent to the API.
 * It captures information about user interaction with a specific event.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicEventRequest{
	private UtmCode utmCode;
	private String assetId;
	private String rewardProgramCode;
	private String cookieId;
	private String event;
	private String deviceInfo;
	private String url;
	private String sid;

	/**
	 * This nested class represents a UTM code object containing a single UTM parameter.
	 */
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UtmCode{
		private String content;
	}
}
