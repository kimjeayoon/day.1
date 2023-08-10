package Insa;

public class InsaDTO {

		String name;
		int age;
		
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public void setName(String name) {
			this.name = name;
		}
		public InsaDTO(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		

}
