package dto;

// Data Transfer Object : 데이터 단위의 클래스
// == VO (Value Object)
public class StudentDto {

  // 학생의 항목들
  private String name;
  private int age;
  private double height;
  private String address;
  private int kor;
  private int eng;
  private int math;

  public StudentDto() { // 생성자

  }

  public StudentDto(String name, int age, double height, String address, int kor, int eng, int math) {
    super(); // 상속할 때 나오는 개념, 지금은 지워도 됨
    this.name = name;
    this.age = age;
    this.height = height;
    this.address = address;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }

  // setter, getter : 외부에서 값을 갖고와야 함
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getKor() {
    return kor;
  }

  public void setKor(int kor) {
    this.kor = kor;
  }

  public int getEng() {
    return eng;
  }

  public void setEng(int eng) {
    this.eng = eng;
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
  }

  @Override
  public String toString() { // 출력 확인용
//    return "StudentDto [name=" + name + ", age=" + age + ", height=" + height + ", address=" + address + ", kor=" + kor + ", eng=" + eng + ", math="
//        + math + "]";

    return "StudentDto [" + name + "-" + age + "=" + height + "=" + address + "=" + kor + "=" + eng + "=" + math + "]";
  }

  public void print() {
    System.out.println("이름:" + name + ", 나이:" + age + ", 키:" + height + ", 주소:" + address + ", 국어점수:" + kor + ", 영어점수:" + eng + ", 수학점수:" + math);

  }

}
