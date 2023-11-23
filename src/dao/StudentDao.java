package dao;

import java.util.Scanner;

import dto.StudentDto;
import file.FileIO;

// Data Access Object : 데이터를 취급하는 클래스
public class StudentDao {
  Scanner sc = new Scanner(System.in);

  private StudentDto student[];

  private int count;

  private FileIO fio;

  // 추가, 삭제, 검색, 수정 (CRUD)
  public StudentDao() { // 생성자

    fio = new FileIO("student");
    fio.create();

    count = 0;

    student = new StudentDto[10]; // 변수 생성 O , 객체 생성 아님 // student1, student2, student3...

    // dto를 생성
    /*
    for (int i = 0; i < student.length; i++) {
      student[i] = new StudentDto();
    }
     */
  }

  public void insert() { // 추가
    System.out.println("학생 정보 입력입니다");

    System.out.print("이름 >> ");
    String name = sc.next();

    System.out.print("나이 >> ");
    int age = sc.nextInt();

    System.out.print("신장 >> ");
    double height = sc.nextDouble();

    System.out.print("주소 >> ");
    String address = sc.next();

    System.out.print("국어 >> ");
    int kor = sc.nextInt();

    System.out.print("영어 >> ");
    int eng = sc.nextInt();

    System.out.print("수학 >> ");
    int math = sc.nextInt();

    student[count] = new StudentDto(name, age, height, address, kor, eng, math);
    count++; // 계속 0번째에 데이터를 넣는 것을 방지하기 위해 배열의 다음으로 이동하게 만드는 코드

  }

  public void delete() { // 삭제
    // 이름 입력
    System.out.print("정보를 삭제할 학생의 이름 >> ");
    String name = sc.next();

    int index = search(name);

    if (index == -1) { // 정보를 찾을 수 없습니다
      System.out.println("학생명단에 없습니다");
      return;
    }

    student[index].setName("");
    student[index].setAge(0);
    student[index].setHeight(0.0);
    student[index].setAddress("");
    student[index].setKor(0);
    student[index].setEng(0);
    student[index].setMath(0);

    System.out.println(name + " 학생 데이터를 삭제하였습니다");
  }

  public void select() { // 검색
    // 이름 입력
    System.out.print("정보를 검색할 학생의 이름 >> ");
    String name = sc.next();

    for (int i = 0; i < student.length; i++) {
      StudentDto dto = student[i];
      if (dto != null && !dto.getName().equals("") == false) {
        if (name.equals(dto.getName())) {
          dto.print();
        }
      }
    }

    int index = search(name);

    if (index == -1) {
      System.out.println("학생명단에 없습니다");
      return;
    }

    System.out.print("이름: " + student[index].getName());
    System.out.print("나이: " + student[index].getAge());
    System.out.print("신장: " + student[index].getHeight());
    System.out.print("주소: " + student[index].getAddress());
    System.out.print("국어: " + student[index].getKor());
    System.out.print("영어: " + student[index].getEng());
    System.out.print("수학: " + student[index].getMath());
  }

  public void update() { // 수정
    // 이름 입력
    System.out.print("정보를 수정할 학생의 이름 >> ");
    String name = sc.next();

    int index = search(name);

    if (index == -1) {
      System.out.println("학생명단에 없습니다");
      return;
    }

    // 국어, 영어, 수학 점수 수정
    System.out.println("수정할 데이터를 찾았습니다");

    System.out.print("국어 >> ");
    int kor = sc.nextInt();

    System.out.print("영어 >> ");
    int eng = sc.nextInt();

    System.out.print("수학 >> ");
    int math = sc.nextInt();

    student[index].setKor(kor);
    student[index].setEng(eng);
    student[index].setMath(math);

    System.out.println("수정을 완료했습니다");
  }

  public int search(String name) {
    int index = -1;
    for (int i = 0; i < student.length; i++) { // 찾았다
      StudentDto dto = student[i];
      if (dto != null) {
        if (name.equals(dto.getName())) { // getter, setter 다시 개념파악
          index = i;
          break;
        }
      }
    }
    return index;
  }

  public void allData() {
    for (int i = 0; i < student.length; i++) {
      StudentDto dto = student[i];
      if (dto != null) {
        System.out.println(dto.toString());
      }
    }
  }

  public void save() {

    // 실제로 삭제된 데이터를 제외한 (정상적인) 데이터가 몇 개?
    int ci = 0;
    for (int i = 0; i < student.length; i++) {
      if (student[i].getName() != null && student[i].getName().equals("") == false) {
        ci++;
      }
    }

    // 배열
    String arr[] = new String[ci];
    int j = 0;
    for (int i = 0; i < student.length; i++) {
      if (student[i].getName() != null && student[i].getName().equals("") == false) {

        arr[j] = student[i].toString();
        j++;
      }
    }

  }

  public void load() {
    String arr[] = fio.dataLoad();

    if (arr == null || arr.length == 0) { // 데이터가 없을 때로 가정
      count = 0; // 인덱스 카운트
      return;
    }

    count = arr.length;

    // String[] -> student[]
    for (int i = 0; i < arr.length; i++) {
      String split[] = arr[i].split("-"); // 문자열 자르기

      // 자른 문자열을 dto에 넣기 위한 처리
      String name = split[0];
      int age = Integer.parseInt(split[1]);
      double height = Double.parseDouble(split[2]);
      String address = split[3];
      int kor = Integer.parseInt(split[4]);
      int eng = Integer.parseInt(split[5]);
      int math = Integer.parseInt(split[6]);

      student[i] = new StudentDto(name, age, height, address, kor, eng, math);
      // student[i] 대신 StudentDto 넣어도 됨
    }

    System.out.println("데이터를 불러오는데 성공했습니다");
  }
}
