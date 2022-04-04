package com.company;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Objects;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JButton buttonLoadFromInputFile;
    private JButton buttonWithStack;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JButton buttonWithMyStack;
    private JButton buttonWithRecursion;
    private JTextField textField;

    public FrameMain() {
        this.setTitle("Проверка правильности скобок");
        this.setContentPane(panelMain);
        this.setBounds(125, 50, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 50, false, true, false, true);
        tableInput.setRowHeight(30);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("C:\\Users\\12\\IdeaProjects\\Таски 2 сем\\Task3_26_2\\"));
        fileChooserSave.setCurrentDirectory(new File("C:\\Users\\12\\IdeaProjects\\Task3_26_2\\"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        buttonLoadFromInputFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {

                    String[] array = ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());

                    assert array != null;
                    JTableUtils.writeArrayToJTable(tableInput, array);
                }
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("Невозможно прочитать данные из файла", "Ошибка");
            }
        });

        buttonWithStack.addActionListener(actionEvent -> {
            try {
                String[] array = JTableUtils.readStringArrayFromJTable(tableInput);
                String str ="";
                if (array[0].equals("")){
                    textField.setText("Ничего не введено");
                } else {
                        for (int i = 0; i < Objects.requireNonNull(array).length; i++) {
                          str = str + array[i];
                        }
                        Logic logic = new Logic();

                        if (logic.bracketStructureValidationWithStack(str.strip())) {
                            textField.setText("Ответ: скобки расположены верно!");
                        } else {
                            textField.setText("Ответ: скобки расположены НЕверно или в введенных данных содержатся недопустимые символы!");
                        }
                    }
                }
            catch (Exception e) {
                SwingUtils.showInfoMessageBox("Пусто!", "Ошибка");
            }
        });

        buttonWithMyStack.addActionListener(actionEvent -> {
            String[] array = JTableUtils.readStringArrayFromJTable(tableInput);
            String str ="";
                try {
                    if (array[0].equals("")){
                        textField.setText("Ничего не введено");
                    } else {
                        for (int i = 0; i < Objects.requireNonNull(array).length; i++) {
                            str = str + array[i];
                        }
                        Logic logic = new Logic();

                        if (logic.bracketStructureValidationWithMyStack(str.strip())) {
                            textField.setText("Ответ: скобки расположены верно!");
                        } else {
                            textField.setText("Ответ: скобки расположены НЕверно или в введенных данных содержатся недопустимые символы!");
                        }
                    }

                }
                catch (Exception e) {
                    SwingUtils.showInfoMessageBox("Пусто!", "Ошибка");
                }
        });

        buttonWithRecursion.addActionListener(actionEvent -> {
            try {
                String[] array = JTableUtils.readStringArrayFromJTable(tableInput);
                String str = "";
                if (array[0].equals("")){
                    textField.setText("Ничего не введено");
                } else {
                    for (int i = 0; i < Objects.requireNonNull(array).length; i++) {
                        str = str + array[i];
                    }
                    Logic logic = new Logic();

                    if (logic.checkForCorrectness(str.strip())) {
                        textField.setText("Ответ: скобки расположены верно!");
                    } else {
                        textField.setText("Ответ: скобки расположены НЕверно или в введенных данных содержатся недопустимые символы!");
                    }
                }

            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("Пусто!", "Ошибка");
            }

        });
    }
}
