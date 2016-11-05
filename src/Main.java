import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

/**
 * @author scriptim
 */
public class Main {

  private static JFrame frame = new JFrame();

  private static JLabel label2 = new JLabel("Binary");
  private static JLabel label8 = new JLabel("Octal");
  private static JLabel label10 = new JLabel("Decimal");
  private static JLabel label16 = new JLabel("Hexadecimal");

  private static JTextField text2 = new JTextField();
  private static JTextField text8 = new JTextField();
  private static JTextField text10 = new JTextField();
  private static JTextField text16 = new JTextField();

  public static void main(String[] args) {

    label2.setToolTipText("Base 2");
    label8.setToolTipText("Base 8");
    label10.setToolTipText("Base 10");
    label16.setToolTipText("Base 16");

    label2.setLabelFor(text2);
    label8.setLabelFor(text8);
    label10.setLabelFor(text10);
    label16.setLabelFor(text16);

    text2.addActionListener(new ReturnListener());
    text8.addActionListener(new ReturnListener());
    text10.addActionListener(new ReturnListener());
    text16.addActionListener(new ReturnListener());

    text2.setToolTipText("0-1");
    text8.setToolTipText("0-7");
    text10.setToolTipText("0-9");
    text16.setToolTipText("0-9, a-f, A-F");

    text2.setDocument(makeDocument(text2, "01"));
    text8.setDocument(makeDocument(text8, "01234567"));
    text10.setDocument(makeDocument(text10, "0123456789"));
    text16.setDocument(makeDocument(text16, "0123456789abcdefABCDEF"));

    frame.setTitle("Number Converter by Scriptim");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(8, 1));
    frame.setSize(500, 300);
    frame.setLocationRelativeTo(null);

    frame.add(label2);
    frame.add(text2);
    frame.add(label8);
    frame.add(text8);
    frame.add(label10);
    frame.add(text10);
    frame.add(label16);
    frame.add(text16);

    frame.setVisible(true);

  }

  private static class ReturnListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      String[] results;

      if (e.getSource() == Main.text2) {
        results = convert(text2.getText(), 2);
      } else if (e.getSource() == Main.text8) {
        results = convert(text8.getText(), 8);
      } else if (e.getSource() == Main.text10) {
        results = convert(text10.getText(), 10);
      } else if (e.getSource() == Main.text16) {
        results = convert(text16.getText(), 16);
      } else {
        results = new String[]{"0", "0", "0", "0"};
      }

      text2.setText(results[0]);
      text8.setText(results[1]);
      text10.setText(results[2]);
      text16.setText(results[3]);

    }

  }

  private static String[] convert(String input, int base) {

    String[] nums = new String[4];
    BigInteger num = input.isEmpty() ? new BigInteger("0") : new BigInteger(input, base);

    nums[0] = num.toString(2);
    nums[1] = num.toString(8);
    nums[2] = num.toString(10);
    nums[3] = num.toString(16);

    return nums;

  }

	private static AbstractDocument makeDocument(JTextField text, String valid) {

    AbstractDocument doc = (AbstractDocument) text.getDocument();
    DocumentFilter filter = new DocumentFilter() {
      
			@Override
      public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
          throws BadLocationException {
        StringBuilder builder = new StringBuilder(string);
        for (int i = builder.length() - 1; i >= 0; i--) {
          char c = builder.charAt(i);
          if(valid.indexOf(c) < 0) {
            builder.deleteCharAt(i);
          }
        }
        super.insertString(fb, offset, builder.toString(), attr);
      }

      @Override
      public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr)
          throws BadLocationException {
        if (length > 0) fb.remove(offset, length);
        insertString(fb, offset, string, attr);
      }
    };

    doc.setDocumentFilter(filter);
    return doc;

  }

}
