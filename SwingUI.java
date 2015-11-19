import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SwingUI extends JFrame implements UI {
   GameProperties gameProps;
   public int width, height;
   PlayerInput lIn, rIn;

   class GamePanel extends JComponent {
      public GamePanel(GameProperties gameProps_) {
         gameProps = gameProps_;
         width = (int)(gameProps.sideWidth*2 / 1000);
         height = (int)(gameProps.netHeight*3 / 1000);

         setPreferredSize(new Dimension(width, height));
         setBackground(Color.white);
      }

      GameState state;

      public int trY(long y) {
         return height - (int)(y/1000);
      }

      public int trX(long x) {
         return (int)((gameProps.sideWidth+x)/1000);
      }

      public int trR(long r) {
         return (int)(r/1000);
      }

      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         paintState(g);
      }

      public void drawCircle(Graphics g, PhysicsCircle c) {
         drawCircle(g,trX(c.posX),trY(c.posY),trR(c.radius));
      }

      public void drawCircle(Graphics g, int x, int y, int r) {
         g.drawOval(x-r, y-r, 2*r, 2*r);
      }

      public void paintScore(Graphics g, int rScore, int lScore) {
         String rScoreStr = rScore+"", lScoreStr = lScore+"";
         FontMetrics fm = g.getFontMetrics();
         g.drawString(rScoreStr, 0, fm.getHeight());
         g.drawString(lScoreStr, width-fm.stringWidth(lScoreStr), fm.getHeight());
      }

      public void paintState(Graphics g) {
         if (g != null && state != null) {
            g.clearRect(0, 0, getWidth(), getHeight());
            MatchState match = state.match;
            PhysicsCircle l = match.lPlayer.pCircle, r = match.rPlayer.pCircle, b = match.ball.pCircle;
            drawCircle(g, l);
            drawCircle(g, r);
            if (trY(b.posY - b.radius) < 0) {
               drawCircle(g, trX(b.posX), 2, 2);
            } else {
               drawCircle(g, b);
            }
            g.drawLine(trX(0),trY(0),trX(0),trY(gameProps.netHeight));
            paintScore(g,state.lScore,state.rScore);
            Toolkit.getDefaultToolkit().sync(); // Not sure why this is necessary, but it makes things a lot smoother
         }
      }

      public void setState(GameState state_) {
         state = state_;
         repaint();
      }
   }

   GamePanel gpanel;

   public SwingUI(GameProperties gameProps, StaticPlayerInputProvider lInput, StaticPlayerInputProvider rInput) {
      lIn = lInput == null ? null : lInput.in;
      rIn = rInput == null ? null : rInput.in;
      addKeyListener(new GameKeyListener());

      gpanel = new GamePanel(gameProps);
      Container content = getContentPane();
      content.setLayout(new BorderLayout());
      content.add(gpanel, BorderLayout.CENTER);
      setTitle("Volley");
      setResizable(false);
      pack();
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }

   @Override
   public void init(GameState state) {
      setVisible(true);
      gpanel.setState(state);
   }

   @Override
   public void display(GameState state) {
      gpanel.setState(state);
   }

   @Override
   public void finish(GameState state) { }

   public class GameKeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         char c = e.getKeyChar();
         if (lIn != null) {
            if (c == 'a') {
               lIn.left = true;
            } else if (c == 'd') {
               lIn.right = true;
            } else if (c == 'w') {
               lIn.up = true;
            }
         }
         if (rIn != null) {
            if (c == 'j') {
               lIn.left = true;
            } else if (c == 'l') {
               lIn.right = true;
            } else if (c == 'i') {
               lIn.up = true;
            }
         }
      }
      public void keyReleased(KeyEvent e) {
         char c = e.getKeyChar();
         if (lIn != null) {
            if (c == 'a') {
               lIn.left = false;
            } else if (c == 'd') {
               lIn.right = false;
            } else if (c == 'w') {
               lIn.up = false;
            }
         }
         if (rIn != null) {
            if (c == 'j') {
               lIn.left = false;
            } else if (c == 'l') {
               lIn.right = false;
            } else if (c == 'i') {
               lIn.up = false;
            }
         }
      }
   }
}
