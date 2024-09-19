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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class CR_UpdateDelteDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField ctNameTextField;
	private JTextField ctDateTextField;
	private JTextField crTypeTextField;
	private JTextField crMethodTextField;
	private JTextField ctClassificationTextField;
	private JTextArea crNoteTextArea;
	private int crId;
    private Connection conn;

    
	public CR_UpdateDelteDialog(int crId, Date crDate, String crType, String crMethod, String ctClassification, String ctName, String crNote) {
		// 데이터베이스 연결 설정
		this.crId = crId;
		this.conn = getConnection();
		if (conn == null) {
	        JOptionPane.showMessageDialog(this, "데이터베이스 연결을 설정할 수 없습니다.");
	        return;
	    }
		
		setBounds(100, 100, 895, 613);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel titleLabel = new JLabel("상담 내용 수정, 삭제");
			titleLabel.setForeground(Color.BLACK);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			titleLabel.setBounds(286, 10, 281, 56);
			contentPanel.add(titleLabel);
		}
		{
			JButton updateButton = new JButton("수정");
			updateButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					handleUpdate();
				}
			});
			updateButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			updateButton.setBounds(613, 483, 88, 33);
			contentPanel.add(updateButton);
			updateButton.setActionCommand("OK");
			getRootPane().setDefaultButton(updateButton);
		}
		{
			JButton deleteButton = new JButton("삭제");
			deleteButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					handleDelete();
				}
			});
			deleteButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			deleteButton.setBounds(726, 483, 88, 33);
			contentPanel.add(deleteButton);
			deleteButton.setActionCommand("Cancel");
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 196, 649, 249);
		contentPanel.add(scrollPane);
		
		
		crNoteTextArea = new JTextArea();
		crNoteTextArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		scrollPane.setViewportView(crNoteTextArea);
		crNoteTextArea.setText(crNote); // crNote 데이터 가져오기
		
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
		
		ctNameTextField = new JTextField();
		ctNameTextField.setBounds(67, 133, 116, 21);
		ctNameTextField.setColumns(10);
		ctNameTextField.setText(ctName); // ctName 데이터 가져오기
		contentPanel.add(ctNameTextField);
		
		JLabel ct_nameLbl_1 = new JLabel("  상담 일자");
		ct_nameLbl_1.setBackground(Color.LIGHT_GRAY);
		ct_nameLbl_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_nameLbl_1.setBounds(243, 102, 88, 21);
		ct_nameLbl_1.setOpaque(true);
		contentPanel.add(ct_nameLbl_1);
		
		ctDateTextField = new JTextField();
		ctDateTextField.setColumns(10);
		ctDateTextField.setBounds(226, 133, 116, 21);
		ctDateTextField.setText(crDate.toString()); // crDate 데이터 가져오기 
		contentPanel.add(ctDateTextField);
		
		
		JLabel ct_nameLbl_2 = new JLabel("  상담 유형");
		ct_nameLbl_2.setBackground(Color.LIGHT_GRAY);
		ct_nameLbl_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_nameLbl_2.setBounds(400, 102, 88, 21);
		ct_nameLbl_2.setOpaque(true);
		contentPanel.add(ct_nameLbl_2);
		
		crTypeTextField = new JTextField();
		crTypeTextField.setColumns(10);
		crTypeTextField.setBounds(383, 133, 116, 21);
		crTypeTextField.setText(crType); // crType 데이터 가져오기
		contentPanel.add(crTypeTextField);
		
		
		JLabel ct_nameLbl_3 = new JLabel("  상담 방법");
		ct_nameLbl_3.setBackground(Color.LIGHT_GRAY);
		ct_nameLbl_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_nameLbl_3.setBounds(564, 102, 88, 21);
		ct_nameLbl_3.setOpaque(true);

		contentPanel.add(ct_nameLbl_3);
		
		crMethodTextField = new JTextField();
		crMethodTextField.setColumns(10);
		crMethodTextField.setBounds(547, 133, 116, 21);
		crMethodTextField.setText(crMethod); // crMethod 데이터 가져오기
		contentPanel.add(crMethodTextField);
		
		
		JLabel ct_nameLbl_4 = new JLabel("  대상 구분");
		ct_nameLbl_4.setBackground(Color.LIGHT_GRAY);
		ct_nameLbl_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ct_nameLbl_4.setBounds(715, 102, 88, 21);
		ct_nameLbl_4.setOpaque(true);

		contentPanel.add(ct_nameLbl_4);
		
		ctClassificationTextField = new JTextField();
		ctClassificationTextField.setColumns(10);
		ctClassificationTextField.setBounds(698, 133, 116, 21);
		ctClassificationTextField.setText(ctClassification); // ctClassification 데이터 가져오기
		contentPanel.add(ctClassificationTextField);
		
		System.out.println("Constructor crId: " + crId);
	}
	
	private Connection getConnection() {
        // 데이터베이스 연결 코드
        String url = "jdbc:mariadb://localhost:3306/minitest";
        String user = "root";
        String password = "123456";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("[에러] 드라이버 로드 실패" + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("[에러] 데이터베이스 연결 실패" + e.getMessage());
            return null;
        }
    }
	
	private void handleUpdate() {
		
		if (conn == null) {
	        JOptionPane.showMessageDialog(this, "데이터베이스 연결을 설정할 수 없습니다.");
	        return;
	    }
		
		String updatedCtName = ctNameTextField.getText();
		String updatedCtDateStr = ctDateTextField.getText();
		String updatedCrType = crTypeTextField.getText();
		String updatedCrMethod = crMethodTextField.getText();
		String updatedCtClassification = ctClassificationTextField.getText();
		String updatedCrNote = crNoteTextArea.getText();
		
		boolean recordExists = checkRecordExists(crId);
	    if (!recordExists) {
	        JOptionPane.showMessageDialog(this, "수정할 상담 내용이 존재하지 않습니다.");
	        return;
	    }
	    
	    Date updatedCtDate = null;
		try {
			updatedCtDate = new SimpleDateFormat("yyyy-MM-dd").parse(updatedCtDateStr);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "날짜입력이 올바르지 않습니다. ex)2024-06-12 형식으로 입력해주세요");
			return;
		}
	
		
		 try (PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE counselingrecord JOIN client ON counselingrecord.ct_id = client.ct_id SET client.ct_name=?, counselingrecord.cr_date=?, counselingrecord.cr_type=?, counselingrecord.cr_method=?, client.ct_classification=?, counselingrecord.cr_note=? WHERE counselingrecord.cr_id=?")) {
			 	
			    System.out.println("updatedCtName : " + updatedCtName);
			 	System.out.println("updatedCrType : " + updatedCrType);
			 	System.out.println("updatedCrMethod : " + updatedCrMethod);
			 	System.out.println("updatedCtClassification : " + updatedCtClassification);
			 	System.out.println("updatedCrNote : " + updatedCrNote);
			 	System.out.println("crId : " + crId);

			 
			 	pstmt.setString(1, updatedCtName);
		        pstmt.setDate(2, new java.sql.Date(updatedCtDate.getTime()));
		        pstmt.setString(3, updatedCrType);
		        pstmt.setString(4, updatedCrMethod);
		        pstmt.setString(5, updatedCtClassification);
		        pstmt.setString(6, updatedCrNote);
		        pstmt.setInt(7, crId);
		        
		        int rowsAffected = pstmt.executeUpdate();
		        if (rowsAffected > 0) {
		            JOptionPane.showMessageDialog(this, "성공적으로 수정되었습니다.");
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(this, "내용 수정에 실패했습니다.");
		        }
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(this, "상담 내용 수정 중 오류가 발생했습니다.");
		        e.printStackTrace();
		    }
	}
	
	private void handleDelete() {
		 if (conn == null) {
		        JOptionPane.showMessageDialog(this, "데이터베이스 연결을 설정할 수 없습니다.");
		        return;
		    }
		 
	    try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM counselingrecord WHERE counselingrecord.cr_id = ?")) {
	    	// 삭제할 상담 기록의 ID를 설정
	        pstmt.setInt(1, crId);
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(this, "성공적으로 삭제되었습니다.");
	            dispose();
	        } else {
	            JOptionPane.showMessageDialog(this, "내용 삭제에 실패했습니다.");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "상담 내용 삭제 중 오류가 발생했습니다.");
	        e.printStackTrace();
	    }
	}

	private boolean checkRecordExists(int crId) {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM counselingrecord WHERE cr_id = ?")) {
			pstmt.setInt(1, crId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
