package com.kh.board.model.vo;

public class Category {
	/*
	 * 빌더패턴?
	 * - 자바에서 기존 객체생성방식의 단점을 보완해주는 새로운 객체생성 디자인 패턴
	 * - 생성자에 인자가 많이 있을때 고려해서 사용하면 된다.(필드가 몇개 없는 간단한 클래스에서는 사용하지 않는것을 권장)
	 * 
	 * 기존생성방식 1. 생성자를 이용한 객체생성
	 * 				객체를 생성할 때 생성자의 매개변수로 데이터를 넣어줘서 객체를 생성했음
	 * 				단점 ? 
	 * 				1) 인자가 많을수록 생성자 또한 많아질 수 있음
	 * 					ex) Member클래스의 생성자로 로그인시 필요한 생성자, 마이페이지 조회용생성자, 회원가입용생성자 등등..
	 * 					생성자는 딱 필요로 하는 데이터만 담고있는게 가장 좋으므로 그에 맞는 생성자가 많아질 수 있다.
	 * 				2) 매개변수의 정보를 설명할 수 없기 때문에 잘못된 위치에 데이터를 추가하게될 위험이 있다.
	 * 					ex) email필드에 address에 해당하는 정보가 추가되어도 컴파일상 문제가 없음.
	 * 			2. setter방식을 이용한 객체생성
	 * 				생성자를 이요한 객체생성방법과 비교해봣을때 2번, 매개변수의 정보를 설명할 수 있기 때문에 가독성 확보되나
	 * 				코드의 길이가 길어진다는 단점과 객체의 일관성이 깨질 수 있다는 단점이 존재함.
	 * 
	 * 빌더패턴 생성방식 : 위 두가지 생성방식의 단점을 보완해주는 새로운 디자인 패턴
	 */
	private int categoryNo;
	private String categoryName;

	public Category() {
		
	}

	public Category(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
	}
	
	public static class Builder{//클래스 내부에 새로운 클래스 생성.
		
		private int categoryNo;
		
		private String categoryName;
		
		public Builder(int categoryNo) { // 필수로 들어가는 변수는 생성자를 통해 값을 넣어줌.
			this.categoryNo=categoryNo;
		}
		
		// 멤버변수별 메소드
		public Builder categoryNo(int categoryNo) {
			this.categoryNo = categoryNo;
			return this; //현재 빌더객체를 반환함으러써 메소드 체이닝이 가능하게함.
		}
		
		public Builder categoryName(String categoryName) {
			this.categoryName=categoryName;
			return this;
		}
		
		// 빌더메소드
		public Category build() {
			Category c = new Category();
			
			c.categoryNo = categoryNo;
			c.categoryName = categoryName;
			
			return c;
		}
		
	}
	
	
	
	
	
	
}
