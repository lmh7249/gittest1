package pack_current;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pack_client.ClientDAO;
import pack_counselor.CounselorDAO;
import pack_login.JoinUpDialog;
import pack_login.LoginDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class CR_InsertDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField ct_nameTextField;
	private JTextField cr_dateTextField;
	private JTextField ct_classificationTextField;
	private CR_InsertDialog_Search dialog; 
	private JComboBox cr_typeComboBox;
	private JComboBox cr_methodComboBox;
	private JTextArea noteTextArea;
	
	
	
	
	public CR_InsertDialog() {
		// 사이즈 조정을 위해 메인으로 옮김.
		setBounds(100, 100, 895, 613);
		

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel titleLabel = new JLabel("상담 등록");
			titleLabel.setForeground(Color.BLACK);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			titleLabel.setBounds(352, 10, 147, 56);
			contentPanel.add(titleLabel);
		}
		{
			JButton createButton = new JButton("등록");
			createButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				// counselingrecord 테이블에 데이터 추가  
				CurrentMainDao dao = new CurrentMainDao();
				
				// dao 2번 메서드 사용
				
					int ctId = dao.getCtIdByName(ct_nameTextField.getText());
					String crDate = cr_dateTextField.getText();
					try {
			            // 날짜 형식 변환
			            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			            inputFormat.setLenient(false); // 월과 일에 대한 제약 추가
			            //  SimpleDateFormat 클래스의 setLenient(false) 옵션을 사용하면 날짜 형식 파싱 시 엄격한 검사를 수행
			            Date date = inputFormat.parse(crDate);
			            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			            crDate = outputFormat.format(date);
			        } catch (ParseException ex) {
			            JOptionPane.showMessageDialog(null, "날짜 형식이 올바르지 않습니다.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
					String crType = (String) cr_typeComboBox.getSelectedItem();
					String crMethod = (String) cr_methodComboBox.getSelectedItem();
					String crNote = noteTextArea.getText();
					
					// 로그인 다이얼로그 클래스에서 상담사 id를 가져오는 구문 추가해야함! -> 아래 insertCounselingRecord 첫 번째 매개변수로 넣어야함. 
					
					// -------------------------------------------------------------
					try {
						
						dao.insertCounselingRecord(LoginDialog.loginID, ctId, crDate, crType, crMethod, crNote);
						JOptionPane.showMessageDialog(null, "상담 내용이 등록되었습니다.");
					} catch (Exception e1) {
						 JOptionPane.showMessageDialog(null, "상담 내용 등록에 실패했습니다.\n" + "관리자에게 문의해주세요.");
					} finally {
						dispose();
						
					}
				}
				}
			);
			createButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			createButton.setBounds(613, 483, 88, 33);
			contentPanel.add(createButton);
			createButton.setActionCommand("OK");
			getRootPane().setDefaultButton(createButton);
		}
		{
			JButton cancelButton = new JButton("취소");
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					// 창 끄기
				}
			});
			cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			cancelButton.setBounds(726, 483, 88, 33);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 196, 649, 249);
		contentPanel.add(scrollPane);
		
		noteTextArea = new JTextArea();
		noteTextArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		scrollPane.setViewportView(noteTextArea);
		
		JLabel NoteLbl = new JLabel("   상담 내용");
		NoteLbl.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		NoteLbl.setOpaque(true); // 라벨 불투명으로 변경 -> 라벨에 배경색 지정하려면 이 코드 추가해야함.
		NoteLbl.setBackground(new Color(164, 219, 218));
		NoteLbl.setBounds(42, 196, 123, 249);
	
       
		contentPanel.add(NoteLbl);
		
		JLabel ct_nameLbl = new JLabel(" 이용자 성명");
		ct_nameLbl.setBackground(Color.LIGHT_GRAY);
		ct_nameLbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_nameLbl.setBounds(77, 102, 95, 21);
		ct_nameLbl.setOpaque(true);
		contentPanel.add(ct_nameLbl);
		
		
		
		// ------------------- 이용자명 텍스트 필드 클릭 시, 이용자 조회 창 팝업--------
		// --------------- CR_CreateDialog_2page -> 전용 클래스 사용함. 
		ct_nameTextField = new JTextField();
		ct_nameTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dialog = new CR_InsertDialog_Search();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				setVisible(false);
			}
		});
		ct_nameTextField.setBounds(67, 133, 116, 21);
		contentPanel.add(ct_nameTextField);
		ct_nameTextField.setColumns(10);
		
		JLabel ct_dateLbl = new JLabel("  상담 일자");
		ct_dateLbl.setBackground(Color.LIGHT_GRAY);
		ct_dateLbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_dateLbl.setBounds(243, 102, 88, 21);
		ct_dateLbl.setOpaque(true);
		contentPanel.add(ct_dateLbl);
		
		cr_dateTextField = new JTextField();
		cr_dateTextField.setColumns(10);
		cr_dateTextField.setBounds(226, 133, 116, 21);
		contentPanel.add(cr_dateTextField);
		
		JLabel ct_typeLbl = new JLabel("  상담 유형");
		ct_typeLbl.setBackground(Color.LIGHT_GRAY);
		ct_typeLbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_typeLbl.setBounds(400, 102, 88, 21);
		ct_typeLbl.setOpaque(true);
		contentPanel.add(ct_typeLbl);
		
		JLabel ct_methodLbl = new JLabel("  상담 방법");
		ct_methodLbl.setBackground(Color.LIGHT_GRAY);
		ct_methodLbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_methodLbl.setBounds(564, 102, 88, 21);
		ct_methodLbl.setOpaque(true);

		contentPanel.add(ct_methodLbl);
		
		JLabel ct_classificationLbl = new JLabel("  대상 구분");
		ct_classificationLbl.setBackground(Color.LIGHT_GRAY);
		ct_classificationLbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_classificationLbl.setBounds(715, 102, 88, 21);
		ct_classificationLbl.setOpaque(true);

		contentPanel.add(ct_classificationLbl);
		
		ct_classificationTextField = new JTextField();
		ct_classificationTextField.setEditable(false);
		ct_classificationTextField.setColumns(10);
		ct_classificationTextField.setBounds(698, 133, 116, 21);
		contentPanel.add(ct_classificationTextField);
		
		cr_typeComboBox = new JComboBox();
		cr_typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"개별 상담", "가족 상담"}));
		cr_typeComboBox.setBounds(383, 132, 116, 23);
		contentPanel.add(cr_typeComboBox);
		
		cr_methodComboBox = new JComboBox();
		cr_methodComboBox.setModel(new DefaultComboBoxModel(new String[] {"방문 상담", "내방 상담"}));
		cr_methodComboBox.setBounds(549, 132, 116, 23);
		contentPanel.add(cr_methodComboBox);
		
	}

	public void setCtNameTextField(String ct_name, String ct_classification) {
		 ct_nameTextField.setText(ct_name);
		 ct_classificationTextField.setText(ct_classification);
	}
	
}
