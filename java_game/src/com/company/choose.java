package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Random;

public class choose extends JFrame implements ActionListener {
    private JPanel jf = new JPanel();
    private JLabel back=new JLabel();
    private JButton[] imagejb = new JButton[3];
    private JLabel[] name = new JLabel[3];
    private ImageIcon icon[] = new ImageIcon[3];
    private JLabel[] live = new JLabel[3];
    private JLabel[] attack = new JLabel[3];
    private JProgressBar[] livep = new JProgressBar[3];
    private JProgressBar[] attackp = new JProgressBar[3];
    private JButton[] expskill = new JButton[3];
    private String exp[] = {"<html>职业法师，克制战士，同时被射手克制<br>技能是对对方造成25点伤害同时回血27点</html>",
            "<html>职业射手，克制法师，同时被战士克制<br>技能是对对方造成高额50点伤害</html>",
            "<html>职业战士，克制射手，同时被法师克制<br>技能是对对方造成40点伤害，同时将来战斗暴击率提高到30%</html>"};
    private ImageIcon icon1 = new ImageIcon("image//back.jpeg");
    public choose() {
        this.setTitle("选择您的角色");
        jf.setLayout(null);
        icon1.setImage(icon1.getImage().getScaledInstance(1300, 650, Image.SCALE_DEFAULT));
        back.setBounds(0,0,1300,650);
        back.setIcon(icon1);
        for (int i = 0; i < 3; i++) {
            name[i] = new JLabel();
            icon[i] = new ImageIcon("image//" + i + ".gif");
            icon[i].setImage(icon[i].getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT));
            imagejb[i] = new JButton(icon[i]);
            imagejb[i].setBounds(425 * i + 75, 50, 300, 400);
            name[i].setBounds(425 * i + 185, 475, 200, 30);
            imagejb[i].addActionListener(this);
            name[i].setFont(new Font(null, Font.BOLD, 22));
            name[i].setForeground(Color.pink);
            live[i] = new JLabel("生命");
            attack[i] = new JLabel("攻击");
            live[i].setForeground(Color.pink);
            attack[i].setForeground(Color.pink);
            live[i].setBounds(425 * i + 75, 520, 30, 30);
            attack[i].setBounds(425 * i + 75, 550, 30, 30);
            livep[i] = new JProgressBar();
            attackp[i] = new JProgressBar();
            livep[i].setBounds(425 * i + 105, 520, 270, 30);
            attackp[i].setBounds(425 * i + 105, 550, 270, 30);
            expskill[i] = new JButton("技能简介");
            expskill[i].addActionListener(this);
            expskill[i].setBounds(425 * i + 185, 580, 100, 40);
            jf.add(live[i]);
            jf.add(attack[i]);
            jf.add(livep[i]);
            jf.add(attackp[i]);
            jf.add(imagejb[i]);
            jf.add(name[i]);
            jf.add(expskill[i]);
        }
        imagejb[0].setToolTipText("ajshjkadwajkdkjdhwk");
        livep[0].setValue(60);
        livep[1].setValue(43);
        livep[2].setValue(65);
        attackp[0].setValue(65);
        attackp[1].setValue(78);
        attackp[2].setValue(63);
        name[0].setText("master");
        name[1].setText("shooter");
        name[2].setText("worrior");
        jf.add(back);
        this.add(jf);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 50, 1300, 650);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            if (e.getSource() == imagejb[i]) {
                System.out.print(i);
                game a = new game(i);
                this.dispose();
                break;
            }
            if (e.getSource() == expskill[i]) {
                JOptionPane.showMessageDialog(
                        jf,
                        exp[i],
                        "技能简介",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
}

class game extends JFrame implements ActionListener, ChangeListener, Runnable {
    private JPanel jf = new JPanel();
    private JLabel back=new JLabel();
    Actor hero[] = new Actor[2];
    private JLabel[] chara = new JLabel[2];
    private ImageIcon icon[] = new ImageIcon[3];
    private JLabel vs = new JLabel("VS");
    private JLabel progress[] = new JLabel[2];
    private JButton[] action = new JButton[3];
    private JProgressBar[] blood = new JProgressBar[2];
    private JLabel text = new JLabel("开始游戏，请选择英雄行动");
    private ImageIcon icon1 = new ImageIcon("image//fight.jpeg");

    public game(int id) {
        icon1.setImage(icon1.getImage().getScaledInstance(1300, 650, Image.SCALE_DEFAULT));
        back.setBounds(0,0,1300,650);
        back.setIcon(icon1);
        Random rand1 = new Random();
        hero[0] = new Actor(attacknum.blood[id],id,6 );
        int rand = rand1.nextInt(3);
        hero[1] = new Actor(attacknum.blood[rand],rand,6 );
        int index[] = {id, rand};
        this.setTitle("游戏开始");
        vs.setBounds(600, 180, 300, 100);
        vs.setFont(new Font(null, Font.BOLD, 90));
        text.setBounds(525, 550, 400, 60);
        text.setFont(new Font(null, Font.BOLD, 28));
        for (int i = 0; i < 3; i++) {
            icon[i] = new ImageIcon("image//" + i + ".gif");
            icon[i].setImage(icon[i].getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT));
            action[i] = new JButton();
            action[i].setBounds(425 * i + 155, 475, 200, 50);
            action[i].setFont(new Font(null, Font.ROMAN_BASELINE, 30));
            action[i].addActionListener(this);
            jf.add(action[i]);
        }
        for (int i = 0; i < 2; i++) {
            chara[i] = new JLabel();
            chara[i].setBounds(200 + 600 * i, 50, 300, 400);
            blood[i] = new JProgressBar();
            progress[i] = new JLabel("  " + hero[i].maxblood + " / " + hero[i].maxblood);
            progress[i].setBounds(300 + 600 * i, 20, 300, 20);
            blood[i].setMinimum(0);
            blood[i].setMaximum(attacknum.blood[index[i]]);
            blood[i].setValue(attacknum.blood[index[i]]);
            blood[i].setBounds(200 + 600 * i, 20, 300, 20);
            blood[i].setBackground(Color.red);
            blood[i].addChangeListener(this);
            chara[i].setIcon(icon[index[i]]);
            progress[i].setForeground(Color.pink);
            jf.add(progress[i]);
            jf.add(chara[i]);
            jf.add(blood[i]);
        }
        action[0].setText("防守");
        action[1].setText("普攻");
        action[2].setText("技能");
        jf.add(text);
        jf.add(vs);
        jf.add(back);
        jf.setLayout(null);
        this.add(jf);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 50, 1300, 650);
    }

    public void move(int id, int flag) {
        String textmessage[] = {"我方英雄", "敌方英雄"};
        int other = (id == 0 ? 1 : 0);
        switch (flag) {
            case 0:
                hero[id].setdefence(true);
                text.setText(textmessage[id] + "进行了防守");
                break;
            case 1:
                hero[id].attack(hero[other]);
                text.setText(textmessage[id] + "进行了普通攻击");
                break;
            case 2:
                hero[id].skill(hero[other]);
                text.setText(textmessage[id] + "用了技能");
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread td = new Thread(this);
        boolean close=false;
        for (int i = 0; i < 3; i++)
            if (e.getSource() == action[i]) {
                move(0, i);
                hero[1].setdefence(false);
                break;
            }
        td.start();
        if (!close&&isover()) {
            this.dispose();
            choose window = new choose();
            window.setVisible(true);
            close=true;
        }
        Random rand1 = new Random();
        int rand = rand1.nextInt(3);
        move(1, rand);
        hero[0].setdefence(false);
        if (!close&&isover()) {
            this.dispose();
            choose window = new choose();
            window.setVisible(true);
            close=true;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == blood[0]) {
            progress[0].setText("  " + blood[0].getValue() + " / " + hero[0].maxblood);
        }
        if (e.getSource() == blood[1]) {
            progress[1].setText("  " + blood[1].getValue() + " / " + hero[1].maxblood);
        }
    }

    @Override
    public void run() {
        int timesleep = 100;
        for (int i = blood[1].getValue(); i <= hero[1].getblood(); i++) {
            try {
                Thread.sleep(timesleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blood[1].setValue(i);
        }
        for (int i = blood[1].getValue(); i >= hero[1].getblood(); i--) {
            try {
                Thread.sleep(timesleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blood[1].setValue(i);
        }
        for (int i = blood[0].getValue(); i <= hero[0].getblood(); i++) {
            try {
                Thread.sleep(timesleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blood[0].setValue(i);
        }
        for (int i = blood[0].getValue(); i >= hero[0].getblood(); i--) {
            try {
                Thread.sleep(timesleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blood[0].setValue(i);
        }
    }

    public boolean isover() {
        if (hero[0].getblood() <= 0) {
            JOptionPane.showMessageDialog(
                    jf,
                    "您已经死亡，敌人获胜",
                    "游戏结束",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        }
        if (hero[1].getblood() <= 0) {
            JOptionPane.showMessageDialog(
                    jf,
                    "敌人已经死亡，您获胜了",
                    "游戏结束",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        }
        return false;
    }
}
