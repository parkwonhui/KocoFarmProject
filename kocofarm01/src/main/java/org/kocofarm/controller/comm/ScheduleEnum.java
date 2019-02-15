package org.kocofarm.controller.comm;

public class ScheduleEnum {
	public static final class CHECK{
		public static final int PROJECT_TITLE_LENGHT = 50;			// 프로젝트 길이
		public static final int CATEGORY_TITLE_LENGHT = 50;			// 카테고리 길이
		public static final int CALENDER_DATE_LENGHT = 10;			// 시작,종료 날짜 String 길이
		public static final int CALENDER_COMPLETION_PER_MIN = 0;	// 일정 완료상황 최소 값
		public static final int CALENDER_COMPLETION_PER_MAX = 100;	// 일정 완료상황 최대 값
	}
	
	public static final class ERROR{
		public static final int UNKNOWN_ERROR = -1;
		public static final int PROJECT_NAME_LENGHT_FAIL = 1000;
		public static final int CATEGORY_NAME_LENGHT_FAIL = 1001;
		public static final int CALENDER_TOO_MANY_TEXT = 1002;
		public static final int CALENDER_START_DT_WRONG = 1003;
		public static final int CALENDER_END_DT_WRONG = 1004;
		public static final int CALENDER_COMPLETION_PERCENT_WRONG = 1005;
		public static final int AUTH_FAIL = 1006;					// 접근할 수 없는 권한
	};
}
