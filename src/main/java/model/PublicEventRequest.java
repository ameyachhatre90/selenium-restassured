package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UtmCode{
		private String content;
	}
}
