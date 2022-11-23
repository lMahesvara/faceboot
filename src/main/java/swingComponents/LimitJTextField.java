/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingComponents;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Hugo Rivera
 */
public class LimitJTextField extends PlainDocument {
    
   private int max;
   public LimitJTextField(int max) {
      super();
      this.max = max;
   }
   public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
      if (text == null)
         return;
      if ((getLength() + text.length()) <= max) {
         super.insertString(offset, text, attr);
      }
     }
    }

