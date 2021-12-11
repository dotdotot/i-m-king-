import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

// TODO 비밀번호찾기Class
public class FindPw extends JFrame {
    // TODO 정보
	public static ArrayList<User> userList = new ArrayList<User>();

	public FindPw(ArrayList<User> user) {
		System.out.println("비밀번호 찾기 폼");
		userList = user;
		// 제목 설정, 메인 스레드 종료시 이벤트 스레드도 종료하도록 설정
		setTitle("비밀번호 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임을 화면 중앙에 배치
		setLocationRelativeTo(null);

		// Main컨테이너 이름을 c로 설정, 배치관리자 삭제, 백그라운드 설정
		Container c = getContentPane();
		c.setLayout(null);

		// 일단 하얀색으로 배경색 설정, 추후 이미지로 바꿀 예정
		c.setBackground(Color.WHITE);

		// TODO 이전
		RoundedButton backButton = new RoundedButton("이전");
		// JButton backButton = new JButton("이전");
		backButton.setBounds(10, 10, 60, 25);
		backButton.setBackground(Color.WHITE);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				new Login(user);
			}
		});

		// TODO 아이디
		JLabel idLabel = new JLabel("아이디");
		idLabel.setForeground(Color.WHITE);
		idLabel.setBounds(250, 56, 295, 30);

		JTextField idTextField = new JTextField();
		idTextField.setBounds(250, 86, 250, 40);
		// TODO 이름
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(250, 136, 295, 30);

		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(250, 166, 250, 40);
		// TODO 휴대폰 번호
		JLabel phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setForeground(Color.WHITE);
		phoneLabel.setBounds(250, 216, 295, 30);

		JTextField phoneTextField = new JTextField();
		phoneTextField.setBounds(250, 246, 250, 40);

		// TODO 비밀번호 찾기
		RoundedButton findPwButton = new RoundedButton("비밀번호 찾기");
		findPwButton.setBackground(Color.WHITE);
		findPwButton.setBounds(305, 306, 140, 40);
		findPwButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String id = idTextField.getText();
				String name = nameTextField.getText();
				String phone = phoneTextField.getText();
				
				boolean check = false;
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getId().equals(id)) {
						if (userList.get(i).getName().equals(name)) {
							if (userList.get(i).getPhoneNumber().equals(phone)) {
								JOptionPane.showMessageDialog(null, "비밀번호 : " + userList.get(i).getPw());
								check = true;
								break;
							}
						}
					}
				}

				if (check) {
					setVisible(false);
					new Login(user);
				} else {
					JOptionPane.showMessageDialog(null, "정보가 틀렸습니다.");
				}

			}
		});

		c.add(backButton);
		c.add(idLabel);
		c.add(idTextField);
		c.add(nameLabel);
		c.add(nameTextField);
		c.add(phoneLabel);
		c.add(phoneTextField);
		c.add(findPwButton);

        // TODO 배경 함수
		backgroundImage();

		setSize(710, 400);
		setVisible(true);
	}

    // TODO 배경 함수 코드
	// 12월~2월 겨울 3월~5월 봄 6월~8월 여름 9월~11월 가을
	// 신정(1/1) 삼일정(3/1) 어린이날(5/5) 현충일(6/6) 광복절(8/15) 개천절(10/3) 한글날(10/9) 성탄절(12/25)
	public void backgroundImage() {
		Date now = new Date();
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		String month = monthFormat.format(now);
		String day = dayFormat.format(now);

		JLabel background = new JLabel();

		// 봄
		if (month.equals("3") || month.equals("4") || month.equals("5")) {
			background = new JLabel(new ImageIcon("images/봄.jpg"));
			if (month.equals("3") && day.equals("1")) {
				background = new JLabel(new ImageIcon("images/삼일절.jpg"));
			}
			if (month.equals("5") && day.equals("5")) {
				background = new JLabel(new ImageIcon("images/어린이날.jpg"));
			}
		}
		// 여름
		else if (month.equals("6") || month.equals("7") || month.equals("8")) {
			background = new JLabel(new ImageIcon("images/여름.jpg"));
			if (month.equals("6") && day.equals("6")) {
				background = new JLabel(new ImageIcon("images/현충일.jpg"));
			}

			if (month.equals("8") && day.equals("15")) {
				background = new JLabel(new ImageIcon("images/광복절.jpg"));
			}
		}
		// 가을
		else if (month.equals("9") || month.equals("10") || month.equals("11")) {
			background = new JLabel(new ImageIcon("images/가을.jpg"));
			if (month.equals("10") && day.equals("3")) {
				background = new JLabel(new ImageIcon("images/개천절.jpg"));
			}
			if (month.equals("10") && day.equals("9")) {
				background = new JLabel(new ImageIcon("images/한글날.jpg"));
			}
		}
		// 겨울
		else if (month.equals("12") || month.equals("1") || month.equals("2")) {
			background = new JLabel(new ImageIcon("images/겨울.jpg"));
			if (month.equals("12") && day.equals("25")) {
				background = new JLabel(new ImageIcon("images/성탄절.jpg"));
			}
			if (month.equals("1") && day.equals("1")) {
				background = new JLabel(new ImageIcon("images/새해.jpg"));
			}
		}
		background.setBounds(0, 0, 710, 400);
		super.add(background);
	}

	// 이미지에따라 글짜 색 바뀌는거
	// 비밀번호 찾기 성공시 메일로 받기 외부 jar파일 불러오는거 궁금함 자꾸 오류남
	// 메일로 받고 싶은데.. 메일은 시간이 없어서 안될 수도 있음.

	public static void main(String[] args) {
	}
}
