import java.awt.*;
import javax.swing.*;
public class SwingUI extends JFrame implements UI {
   GameProperties gameProps;
   public int width, height;

   class GamePanel extends JPanel {
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
         if (state != null) {
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
         }
      }

      public void drawCircle(Graphics g, PhysicsCircle c) {
         drawCircle(g,trX(c.posX),trY(c.posY),trR(c.radius));
      }

      public void drawCircle(Graphics g, int x, int y, int r) {
         g.drawOval(x-r, y-r, 2*r, 2*r);
      }

      public void setState(GameState state_) {
         state = state_;
         repaint();
      }
   }

   GamePanel gpanel;

   public SwingUI(GameProperties gameProps) {
      gpanel = new GamePanel(gameProps);
      Container content = this.getContentPane();
      content.setLayout(new BorderLayout());
      content.add(gpanel, BorderLayout.CENTER); // Note 2
      setTitle("Volley");
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
}
