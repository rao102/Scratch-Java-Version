import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;

public class ShapeMover3
{
    public ShapeMover3() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Shape Mover Test Level");
		frame.setSize(1200,800);

        initComponents(frame);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String s[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShapeMover3();
            }
        });
    }

    private void initComponents(JFrame frame) {
		DragPanel d1 = new DragPanel();
		frame.getContentPane().add(d1);
    }
}

class DragPanel extends JPanel {

	// Reference Object Settings
	String oname = "";
	
	int fake_x = (1)-1; // Space in parenthesis
	int fake_y = (1)-1;
	
	int realx = 535+80*fake_x;
	int realy = 20+80*fake_y;
	
	
	// Settings
	String lastTouched = "", obj_call = "", func_call = "", param_1 = "", param_2 = "";
	boolean hover = false, runHover = false;
	Graphics2D g2d;
	
	// Goal Space
	String obj_goal = "square"; // circle:triangle:square
	
	int x = (8)-1; // Space in parenthesis
	int y = (8)-1;
	
	int GS_x = 535+80*x;
	int GS_y = 20+80*y;
	
	Rectangle GoalSpace = new Rectangle(0, 0, 80, 80);
	
	// Objects
    Rectangle rect = new Rectangle (0, 0, 77, 80);
	Rectangle rect2 = new Rectangle(0, 0, 77, 80);
	Rectangle rect3 = new Rectangle(0, 0, 77, 80);
	
	// Drag Functions
	Rectangle dragF1 = new Rectangle (0, 0, 315, 80);
	Rectangle dragF2 = new Rectangle (0, 0, 315, 80);
	Rectangle dragF3 = new Rectangle (0, 0, 315, 80);
	Rectangle dragF4 = new Rectangle (0, 0, 315, 80);
	
	// Parameters
	// #Numbers
	Rectangle no1 = new Rectangle(0,0,77,80);
	Rectangle no2 = new Rectangle(0,0,77,80);
	Rectangle no3 = new Rectangle(0,0,77,80);
	Rectangle no4 = new Rectangle(0,0,77,80);
	Rectangle no5 = new Rectangle(0,0,77,80);
	Rectangle no6 = new Rectangle(0,0,77,80);
	Rectangle no7 = new Rectangle(0,0,77,80);
	
	// #ClearCommand
	Rectangle Pclear = new Rectangle(0,0,77,80);
	Rectangle Prun = new Rectangle(0,0,77,80);
	
	// #Directions
	Rectangle RNor = new Rectangle(0,0,77,80);
	Rectangle RSou = new Rectangle(0,0,77,80);
	Rectangle REas = new Rectangle(0,0,77,80);
	Rectangle RWes = new Rectangle(0,0,77,80);
	
	// #Mirror
	Rectangle Rleft = new Rectangle(0,0,77,80);
	Rectangle Rright = new Rectangle(0,0,77,80);
	
	// Hover Rectangle
	Rectangle hov;
	int hovX = 0, hovY = 0, hdimX, hdimY;
	String hname = "";
	String hinfo = "";
	String hparam = "";
	String htype = "";
	boolean helpon = true;
	
	
    int preX, preY;
    boolean isFirstTime = true;
    Rectangle area;
    boolean pressOut = false;
	char type;
    private Dimension dim = new Dimension(1200, 800);

    public DragPanel() {
        addMouseMotionListener(new MyMouseAdapter());
        addMouseListener(new MyMouseAdapter());
    }

    public Dimension getPreferredSize() {
        return dim;
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		this.setBackground(new Color(0x6C, 0x7D, 0x97));
		
		Grid gee = new Grid(g);
		
        g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
        if (isFirstTime) {
            area = new Rectangle(dim);
			
            rect.setLocation(15, 12);
			rect2.setLocation(98, 12);
			rect3.setLocation(181,12);
			
			dragF1.setLocation(15,97);
			dragF2.setLocation(15,182);
			dragF3.setLocation(15,267);
			dragF4.setLocation(15,352);

			no1.setLocation(15,438);
			no2.setLocation(98,438);
			no3.setLocation(181,438);
			no4.setLocation(264,438);
			
			no5.setLocation(15,523);
			no6.setLocation(98,523);
			no7.setLocation(181,523);
			Pclear.setLocation(264,523); //(Extra Option)
			
			RNor.setLocation(15,609);
			RSou.setLocation(98,609);
			REas.setLocation(181,609);
			RWes.setLocation(264,609);
			
			Rleft.setLocation(15,695);
			Rright.setLocation(98,695);
			
			Prun.setLocation(1102,675); //(Extra Option)
			
			// Goal Space
			GoalSpace.setLocation(GS_x, GS_y);
			
            isFirstTime = false;
        }

		g2d.setColor(new Color(0x44, 0x53, 0x60));
		g2d.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		g2d.drawString("object", 535, 775);
		
		g2d.setColor(new Color(0x37, 0x42, 0x4D));
		g2d.drawString("function", 617, 775);
		
		if(runHover == false)
		{
			g2d.setColor(new Color(0xCD, 0x01, 0x01));
		}
		else
		{
			g2d.setColor(new Color(0xEB, 0x01, 0x01));
		}
		
		g2d.fill(Prun);
		
		g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
		g2d.drawString("run", 1126, 723);
		
		// Parameter Blocks
		
		g2d.setColor(new Color(0xCD, 0xCD, 0xCD));
		g2d.fill(no1);
		g2d.fill(no2);
		g2d.fill(no3);
		g2d.fill(no4);
		g2d.fill(no5);
		g2d.fill(no6);
		g2d.fill(no7);
		
		g2d.fill(RNor);
		g2d.fill(RSou);
		g2d.fill(REas);
		g2d.fill(RWes);
		
		g2d.fill(Rleft);
		g2d.fill(Rright);
		
		g2d.setColor(new Color(0x37, 0x42, 0x4D));
		g2d.fill(Pclear);
		
		g2d.setColor(new Color(0x00, 0x99, 0xFF));
		g2d.fill(GoalSpace);
		
		//
		g2d.setStroke(new BasicStroke(2));
		
		if(obj_goal.equals("circle"))
		{
			//fill
			g2d.setColor(new Color(0x01, 0x79, 0xC0));
			g2d.fillOval(13+GS_x,15+GS_y,50,50);
			
			//outline
			g2d.setColor(new Color(0x02, 0x6F, 0xA2));
			g2d.drawOval(13+GS_x,15+GS_y,50,50);
		}
		else if(obj_goal.equals("triangle"))
		{
			//fill
			g2d.setColor(new Color(0x01, 0x79, 0xC0));
			int[] px = {13+GS_x+1,39+GS_x+1,63+GS_x+1};
			int[] py = {65+GS_y,15+GS_y,65+GS_y};
			g2d.fillPolygon(px,py,3);
			
			//outline
			g2d.setColor(new Color(0x02, 0x6F, 0xA2));
			g2d.drawLine(13+GS_x+1,65+GS_y,39+GS_x+1,15+GS_y);
			g2d.drawLine(39+GS_x+1,15+GS_y,63+GS_x+1,65+GS_y);
			g2d.drawLine(13+GS_x+1,65+GS_y,63+GS_x+1,65+GS_y);
		}
		else if(obj_goal.equals("square"))
		{
			//fill
			g2d.setColor(new Color(0x01, 0x79, 0xC0));
			g2d.fillRect(13+GS_x+1,15+GS_y+1, 50, 50);
			
			//outline
			g2d.setColor(new Color(0x02, 0x6F, 0xA2));
			g2d.drawLine(13+GS_x+1,15+GS_y,63+GS_x+1,15+GS_y);
			g2d.drawLine(63+GS_x+1,15+GS_y,63+GS_x+1,65+GS_y);
			g2d.drawLine(63+GS_x+1,65+GS_y,13+GS_x+1,65+GS_y);
			g2d.drawLine(13+GS_x+1,65+GS_y,13+GS_x+1,15+GS_y);
		}
		
		g2d.setStroke(new BasicStroke(1));
		//
		
		g2d.setColor(new Color(0x66, 0x66, 0x66));
		g2d.setFont(new Font("Century Gothic", Font.PLAIN, 50));
		g2d.drawString("1", 23+no1.x, 58+no1.y);
		g2d.drawString("2", 23+no2.x, 58+no2.y);
		g2d.drawString("3", 23+no3.x, 58+no3.y);
		g2d.drawString("4", 23+no4.x, 58+no4.y);
		g2d.drawString("5", 23+no5.x, 58+no5.y);
		g2d.drawString("6", 23+no6.x, 58+no6.y);
		g2d.drawString("7", 23+no7.x, 58+no7.y);
		
		g2d.drawString("N", 20+RNor.x, 58+RNor.y);
		g2d.drawString("S", 25+RSou.x, 58+RSou.y);
		g2d.drawString("E", 22+REas.x, 58+REas.y);
		g2d.drawString("W", 16+RWes.x, 58+RWes.y);
		
		g2d.drawString("V", 22+Rleft.x, 58+Rleft.y);
		g2d.drawString("H", 22+Rright.x, 58+Rright.y);
		
		g2d.setColor(new Color(0x6C, 0x7D, 0x97));
		g2d.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		g2d.drawString("clear", 11+Pclear.x, 48+Pclear.y);
		
		// Drag Blocks

		g2d.setColor(new Color(0x00, 0x33, 0x66));
		g2d.fill(dragF1);
		g2d.fill(dragF2);
		g2d.fill(dragF3);
		g2d.fill(dragF4);

		g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
		g2d.setFont(new Font("Century Gothic", Font.PLAIN, 50));

		g2d.drawString("CREATE",10+dragF1.x,58+dragF1.y);
		g2d.drawString("MOVE",10+dragF2.x,58+dragF2.y);
		g2d.drawString("DELETE",10+dragF3.x,58+dragF3.y);
		g2d.drawString("FLIP",10+dragF4.x,58+dragF4.y);
		
		// Object Blocks
		
		g2d.setColor(new Color(0xCD, 0x01, 0x01));
		
		g2d.fill(rect);
		g2d.fill(rect2);
		g2d.fill(rect3);
	
		g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
		g2d.setStroke(new BasicStroke(2));
		g2d.drawOval(13+rect.x,15+rect.y,50,50);
		
		g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
		g2d.drawLine(13+rect2.x,65+rect2.y,39+rect2.x,15+rect2.y);
		g2d.drawLine(39+rect2.x,15+rect2.y,63+rect2.x,65+rect2.y);
		g2d.drawLine(13+rect2.x,65+rect2.y,63+rect2.x,65+rect2.y);
		
		g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
		g2d.drawLine(13+rect3.x,15+rect3.y,63+rect3.x,15+rect3.y);
		g2d.drawLine(63+rect3.x,15+rect3.y,63+rect3.x,65+rect3.y);
		g2d.drawLine(63+rect3.x,65+rect3.y,13+rect3.x,65+rect3.y);
		g2d.drawLine(13+rect3.x,65+rect3.y,13+rect3.x,15+rect3.y);
		
		g2d.setStroke(new BasicStroke(1));
		
		// Reference Object Drawing
		if(oname.equals("circle"))
		{
			g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
			g2d.fillOval(13+realx,15+realy,50,50);
		}
		else if(oname.equals("triangle"))
		{
			g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
			int[] px2 = {13+realx+1,39+realx+1,63+realx+1};
			int[] py2 = {65+realy,15+realy,65+realy};
			g2d.fillPolygon(px2,py2,3);
		}
		else if(oname.equals("square"))
		{
			g2d.setColor(new Color(0xFF, 0xFF, 0xFF));
			g2d.fillRect(13+realx+1,15+realy+1, 50, 50);
		}
		
		// Hover Block
		int onlyOnce = 0;
		
		if(hover == true && helpon == true)
		{
			hov = new Rectangle(hovX,hovY,hdimX,hdimY);
			g2d.setColor(new Color(0xFD, 0xC9, 0x51));
			g2d.fill(hov);
			
			if(htype.equals("object"))
			{
				g2d.setColor(new Color(0xCC, 0x00, 0x00));
			}
				else if(htype.equals("function"))
			{
				g2d.setColor(new Color(0x00, 0x00, 0x99));
			}
			
			g2d.setFont(new Font("Century Gothic", Font.PLAIN, 17));
			g2d.drawString(hname,5+hovX,18+hovY);
			g2d.setColor(new Color(0xA8, 0x6D, 0x02));
			g2d.drawString(hinfo,5+hovX,36+hovY);
			
			if(htype.equals("function"))
			{
				g2d.setColor(new Color(0xA8, 0x6D, 0x02));
				g2d.drawString(hparam,5+hovX,54+hovY);
			}
			
			//
			onlyOnce = 1;
		}
    }

    private class MyMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
			hover = false;
			
			// Object Blocks
			if (rect.contains(e.getX(), e.getY())) {
				lastTouched = "rect";
				preX = rect.x - e.getX();
				preY = rect.y - e.getY();
				updateLocation(e);
			}	else if (rect2.contains(e.getX(), e.getY())) {
				lastTouched = "rect2";
				preX = rect2.x - e.getX();
				preY = rect2.y - e.getY();
				updateLocation(e);
			} else if (rect3.contains(e.getX(), e.getY())) {
				lastTouched = "rect3";
				preX = rect3.x - e.getX();
				preY = rect3.y - e.getY();
				updateLocation(e);
			} 
			// Function Blocks
			else if (dragF1.contains(e.getX(), e.getY())) {
				lastTouched = "dragF1";
				preX = dragF1.x - e.getX();
				preY = dragF1.y - e.getY();
				updateLocation(e);
			} else if (dragF2.contains(e.getX(), e.getY())) {
				lastTouched = "dragF2";
				preX = dragF2.x - e.getX();
				preY = dragF2.y - e.getY();
				updateLocation(e);
			} else if (dragF3.contains(e.getX(), e.getY())) {
				lastTouched = "dragF3";
				preX = dragF3.x - e.getX();
				preY = dragF3.y - e.getY();
				updateLocation(e);
			} else if (dragF4.contains(e.getX(), e.getY())) {
				lastTouched = "dragF4";
				preX = dragF4.x - e.getX();
				preY = dragF4.y - e.getY();
				updateLocation(e);
			}
			// Parameter Blocks
			else if (no1.contains(e.getX(), e.getY())) {
				lastTouched = "no1";
				preX = no1.x - e.getX();
				preY = no1.y - e.getY();
				updateLocation(e);
			}
			else if (no2.contains(e.getX(), e.getY())) {
				lastTouched = "no2";
				preX = no2.x - e.getX();
				preY = no2.y - e.getY();
				updateLocation(e);
			}
			else if (no3.contains(e.getX(), e.getY())) {
				lastTouched = "no3";
				preX = no3.x - e.getX();
				preY = no3.y - e.getY();
				updateLocation(e);
			}
			else if (no4.contains(e.getX(), e.getY())) {
				lastTouched = "no4";
				preX = no4.x - e.getX();
				preY = no4.y - e.getY();
				updateLocation(e);
			}
			else if (no5.contains(e.getX(), e.getY())) {
				lastTouched = "no5";
				preX = no5.x - e.getX();
				preY = no5.y - e.getY();
				updateLocation(e);
			}
			else if (no6.contains(e.getX(), e.getY())) {
				lastTouched = "no6";
				preX = no6.x - e.getX();
				preY = no6.y - e.getY();
				updateLocation(e);
			}
			else if (no7.contains(e.getX(), e.getY())) {
				lastTouched = "no7";
				preX = no7.x - e.getX();
				preY = no7.y - e.getY();
				updateLocation(e);
			}
			else if (RNor.contains(e.getX(), e.getY())) {
				lastTouched = "RNor";
				preX = RNor.x - e.getX();
				preY = RNor.y - e.getY();
				updateLocation(e);
			}
			else if (RSou.contains(e.getX(), e.getY())) {
				lastTouched = "RSou";
				preX = RSou.x - e.getX();
				preY = RSou.y - e.getY();
				updateLocation(e);
			}
			else if (REas.contains(e.getX(), e.getY())) {
				lastTouched = "REas";
				preX = REas.x - e.getX();
				preY = REas.y - e.getY();
				updateLocation(e);
			}
			else if (RWes.contains(e.getX(), e.getY())) {
				lastTouched = "RWes";
				preX = RWes.x - e.getX();
				preY = RWes.y - e.getY();
				updateLocation(e);
			}
			else if (Rleft.contains(e.getX(), e.getY())) {
				lastTouched = "Rleft";
				preX = Rleft.x - e.getX();
				preY = Rleft.y - e.getY();
				updateLocation(e);
			}
			else if (Rright.contains(e.getX(), e.getY())) {
				lastTouched = "Rright";
				preX = Rright.x - e.getX();
				preY = Rright.y - e.getY();
				updateLocation(e);
			}
			// To Clear
			else if (Pclear.contains(e.getX(), e.getY())) {
				lastTouched = "pclear";
				obj_call = "";
				func_call = "";
				param_1 = "";
				param_2 = "";
				
				isFirstTime = true;
				repaint();
			}
			// To Run
			else if (Prun.contains(e.getX(), e.getY())) {
				lastTouched = "prun";
				executeCommand(obj_call, func_call, param_1, param_2);
				
				// reset
				isFirstTime = true;
				repaint();
				
				// Checking to see if the final location is reached
				if(realx == GS_x && realy == GS_y && oname.equals(obj_goal))
				{
					System.out.println("==== LEVEL COMPLETE ====");
				}
			}
			else
			{
				pressOut = true;
			}
			
			System.out.println(lastTouched);
        }
		
		public void executeCommand(String objt, String funci, String p1, String p2)
		{
			if(objt.equals(""))
			{
				// Must have a reference object
				System.out.println("ERROR: MUST HAVE AN OBJECT!");
			}
			else
			{
				switch(funci)
				{
					case "create" :
						createOb(objt);
					break;
					
					case "move":
						if(obj_call.equals(oname))
						{
							moveOb(p1, p2);
						}else{
							System.out.println("ERROR: Reference Object doesn't exist!");
						}
					break;
					
					case "delete":
					break;
					
					case "flip":
					if(obj_call.equals(oname))
					{
						flipOb(p1);
					}else{
						System.out.println("ERROR: Reference Object doesn't exist!");
					}
					break;
					
					case "":
					System.out.println("ERROR: Reference Object must have a command!");
					break;
				}
			}
		}
		
		// =============
		// MINI COMMANDS
		// =============
		public void createOb(String ob)
		{
			oname = ob;
			realx = 535+80*fake_x;
			realy = 20+80*fake_y;
		}
		
		public void moveOb(String p_1, String p_2)
		{
			int mg = 0;
			
			switch(p_1)
			{
				case "no1" :
					mg = 1;
				break;
				
				case "no2" :
					mg = 2;
				break;
				
				case "no3" :
					mg = 3;
				break;
				
				case "no4" :
					mg = 4;
				break;
				
				case "no5" :
					mg = 5;
				break;
				
				case "no6" :
					mg = 6;
				break;
				
				case "no7" :
					mg = 7;
				break;
				
				case "":
				System.out.println("ERROR: Missing Parameters!");
				break;
			}
			
			if(p_2.equals("RNor"))
			{
				boolean boundaryApproval = (realy-(80*mg)) >= 80;
				
				if(boundaryApproval)
				{
					realy = realy-(80*mg);
				}
			}
			else if(p_2.equals("RSou"))
			{
				boolean boundaryApproval = (realy+(80*mg)) <= 580;
				
				if(boundaryApproval)
				{
					realy = realy+(80*mg);
				}
			}
			else if(p_2.equals("REas"))
			{
				boolean boundaryApproval = realx+(80*mg) <= 1095;
				
				if(boundaryApproval)
				{
					realx = realx+(80*mg);
				}
			}
			else if(p_2.equals("RWes"))
			{
				boolean boundaryApproval = realx-(80*mg) >= 535;
				
				if(boundaryApproval)
				{
					realx = realx-(80*mg);
				}
			}else{
				System.out.println("ERROR: Missing Parameters!");
			}
		}
		
		public void flipOb(String p_1)
		{
			if(p_1.equals("Rleft"))
			{
				realy = Math.abs(((580)-realy)+20);
			}
			else if(p_1.equals("Rright"))
			{
				realx = ((1095)-realx)+535;
			}else{
			System.out.println("ERROR: Missing Parameters!");
			}
		}
		// =============
		// =============

        public void mouseDragged(MouseEvent e) {
		
			if (!pressOut) {
				updateLocation(e);
			} else {
			}
			
        }
		
		public void mouseMoved(MouseEvent e)
		{
			if(rect.contains(e.getPoint())){
				hover = true;
				htype = "object";
				
				hovX = 4+e.getX();
				hovY = e.getY()-50;
				hdimX = 250;
				hdimY = 50;
				
				hname = "circle";
				hinfo = "reference object";
				
				repaint();
			}
			else if(rect2.contains(e.getPoint())){
				hover = true;
				htype = "object";
				
				hovX = 4+e.getX();
				hovY = e.getY()-50;
				hdimX = 250;
				hdimY = 50;
				
				hname = "triangle";
				hinfo = "reference object";
				
				repaint();
			}
			else if(rect3.contains(e.getPoint())){
				hover = true;
				htype = "object";
				
				hovX = 4+e.getX();
				hovY = e.getY()-50;
				hdimX = 250;
				hdimY = 50;
				
				hname = "square";
				hinfo = "reference object";
				
				repaint();
			}
			else if(dragF1.contains(e.getPoint())){
				hover = true;
				htype = "function";
				hparam = "parameters: none";
				
				hovX = 4+e.getX();
				hovY = e.getY()-75;
				hdimX = 250;
				hdimY = 75;
				
				hname = "create function";
				hinfo = "creates a reference object";
				
				repaint();
			}
			else if(dragF2.contains(e.getPoint())){
				hover = true;
				htype = "function";
				hparam = "parameters: #, direction";
				
				hovX = 4+e.getX();
				hovY = e.getY()-75;
				hdimX = 250;
				hdimY = 75;
				
				hname = "move function";
				hinfo = "moves a reference object";
				
				repaint();
			}
			else if(dragF3.contains(e.getPoint())){
				hover = true;
				htype = "function";
				hparam = "parameters: direction";
				
				hovX = 4+e.getX();
				hovY = e.getY()-75;
				hdimX = 250;
				hdimY = 75;
				
				hname = "delete function";
				hinfo = "deletes surrounding obstacle";
				
				repaint();
			}
			else if(dragF4.contains(e.getPoint())){
				hover = true;
				htype = "function";
				hparam = "parameters: vert./horiz.";
				
				hovX = 4+e.getX();
				hovY = e.getY()-75;
				hdimX = 250;
				hdimY = 75;
				
				hname = "flip function";
				hinfo = "flips a reference object";
				
				repaint();
			}
			else
			{
				hover = false;
				repaint();
			}
		}

        public void mouseReleased(MouseEvent e) {
		
			// Object Blocks
			
			if (rect.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
		
			if (rect2.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
		
			if (rect3.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			// drag Functions
			
			if (dragF1.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (dragF2.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (dragF3.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (dragF4.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			// parameter functions
			
			// drag Functions
			
			if (no1.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (no2.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (no3.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (no4.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (no5.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (no6.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (no7.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (RNor.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (RSou.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (REas.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (RWes.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (Rleft.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (Rright.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (Pclear.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			if (Prun.contains(e.getX(), e.getY())) {
				updateLocation(e);
			} else {
				pressOut = false;
			}
			
			// SNAP!
		
			// Rect1
			if(lastTouched.equals("rect"))
			{
				if(rect.x > 505 && rect.x < 642 && rect.y > 645 && rect.y < 785)
				{
					rect.setLocation(535, 675);
					rect2.setLocation(98, 12);
					rect3.setLocation(181, 12);
					obj_call = "circle";
				}
				else
				{
					if(!(rect2.x == 535 || rect3.x == 535))
					{
						obj_call = "";
					}
					rect.setLocation(15, 12);
				}
			}
			
			// Rect2
			if(lastTouched.equals("rect2"))
			{
				if(rect2.x > 505 && rect2.x < 642 && rect2.y > 645 && rect2.y < 785)
				{
					rect.setLocation(15, 12);
					rect2.setLocation(535, 675);
					rect3.setLocation(181, 12);
					obj_call = "triangle";
				}
				else
				{
					if(!(rect.x == 535 || rect3.x == 535))
					{
						obj_call = "";
					}
					rect2.setLocation(98, 12);
				}
			}
			
			// Rect3
			if(lastTouched.equals("rect3"))
			{
				if(rect3.x > 505 && rect3.x < 642 && rect3.y > 645 && rect3.y < 785)
				{
					rect.setLocation(15, 12);
					rect2.setLocation(98, 12);
					rect3.setLocation(535, 675);
					obj_call = "square";
				}
				else
				{
					if(!(rect2.x == 535 || rect.x == 535))
					{
						obj_call = "";
					}
					rect3.setLocation(181, 12);
				}
			}
			
			System.out.println("Object: "+obj_call);
			
			// dragF1
			if(lastTouched.equals("dragF1"))
			{
				if(dragF1.x > 505 && dragF1.x < 655 && dragF1.y > 635 && dragF1.y < 795)
				{
					dragF1.setLocation(617, 675);
					dragF2.setLocation(15,182);
					dragF3.setLocation(15,267);
					dragF4.setLocation(15,352);
					func_call = "create";
				}
				else
				{
					if(!(dragF2.x == 617 || dragF3.x == 617 || dragF4.x == 617))
					{
						func_call = "";
					}
					dragF1.setLocation(15, 97);
				}
			}
			
			// dragF2
			if(lastTouched.equals("dragF2"))
			{
				if(dragF2.x > 505 && dragF2.x < 655 && dragF2.y > 635 && dragF2.y < 795)
				{
					dragF1.setLocation(15, 97);
					dragF2.setLocation(617,675);
					dragF3.setLocation(15,267);
					dragF4.setLocation(15,352);
					func_call = "move";
				}
				else
				{
					if(!(dragF1.x == 617 || dragF3.x == 617 || dragF4.x == 617))
					{
						func_call = "";
					}
					dragF2.setLocation(15, 182);
				}
			}
			
			// dragF3
			if(lastTouched.equals("dragF3"))
			{
				if(dragF3.x > 505 && dragF3.x < 655 && dragF3.y > 635 && dragF3.y < 795)
				{
					dragF1.setLocation(15, 97);
					dragF2.setLocation(15, 182);
					dragF3.setLocation(617, 675);
					dragF4.setLocation(15, 352);
					func_call = "delete";
				}
				else
				{
					if(!(dragF2.x == 617 || dragF1.x == 617 || dragF4.x == 617))
					{
						func_call = "";
					}
					dragF3.setLocation(15, 267);
				}
			}
			
			// dragF4
			if(lastTouched.equals("dragF4"))
			{
				if(dragF4.x > 505 && dragF4.x < 655 && dragF4.y > 635 && dragF4.y < 795)
				{
					dragF1.setLocation(15,97);
					dragF2.setLocation(15,182);
					dragF3.setLocation(15,267);
					dragF4.setLocation(617,675);
					func_call = "flip";
				}
				else
				{
					if(!(dragF2.x == 617 || dragF3.x == 617 || dragF1.x == 617))
					{
						func_call = "";
					}
					dragF4.setLocation(15, 352);
				}
			}
			
			System.out.println("Function: "+func_call);
			
			// Create and None
			
			if(func_call.equals("create") || func_call.equals(""))
			{
				//No numbers necessary, No magnitude or Symmetry
				no1.setLocation(15,438);
				no2.setLocation(98,438);
				no3.setLocation(181,438);
				no4.setLocation(264,438);
			
				no5.setLocation(15,523);
				no6.setLocation(98,523);
				no7.setLocation(181,523);
			
				RNor.setLocation(15,609);
				RSou.setLocation(98,609);
				REas.setLocation(181,609);
				RWes.setLocation(264,609);
				
				Rleft.setLocation(15,695);
				Rright.setLocation(98,695);
				
				param_1 = "";
				param_2 = "";
			}
			
			// Delete Function
			
			if(func_call.equals("delete"))
			{
				no1.setLocation(15,438);
				no2.setLocation(98,438);
				no3.setLocation(181,438);
				no4.setLocation(264,438);
			
				no5.setLocation(15,523);
				no6.setLocation(98,523);
				no7.setLocation(181,523);
				
				Rleft.setLocation(15,695);
				Rright.setLocation(98,695);
				
				if(param_2.equals("RNor") || param_2.equals("RSou") || param_2.equals("REas") || 
				param_2.equals("RWes"))
				{
					param_1 = param_2;
				}
				else if(!(param_2.equals("RNor") || param_2.equals("RSou") || param_2.equals("REas") || 
				param_2.equals("RWes")))
				{
					param_1 = "";
				}
				
				param_2 = "";
				
				if(lastTouched.equals("RNor"))
				{
					if(RNor.x > 895 && RNor.x < 1030 && RNor.y > 635 && RNor.y < 705)
					{
						RNor.setLocation(937,675);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_1 = "RNor";
					}
					else if(RNor.x > 1030 && RNor.x < 1120 && RNor.y > 635 && RNor.y < 705)
					{
						RNor.setLocation(937,675);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_1 = "RNor";
					}
					else
					{
						if(!(RSou.x == 937 || REas.x == 937 || RWes.x == 937))
						{
							param_1 = "";
						}
						RNor.setLocation(15,609);
					}
				}
				
				if(lastTouched.equals("RSou"))
				{
					if(RSou.x > 895 && RSou.x < 1030 && RSou.y > 635 && RSou.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(937,675);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_1 = "RSou";
					}
					else if(RSou.x > 1030 && RSou.x < 1120 && RSou.y > 635 && RSou.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(937,675);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_1 = "RSou";
					}
					else
					{
						if(!(RNor.x == 937 || REas.x == 937 || RWes.x == 937))
						{
							param_1 = "";
						}
						RSou.setLocation(98,609);
					}
				}
				
				if(lastTouched.equals("REas"))
				{
					if(REas.x > 895 && REas.x < 1030 && REas.y > 635 && REas.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(937,675);
						RWes.setLocation(264,609);
						param_1 = "REas";
					}
					else if(REas.x > 1030 && REas.x < 1120 && REas.y > 635 && REas.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(937,675);
						RWes.setLocation(264,609);
						param_1 = "REas";
					}
					else
					{
						if(!(RSou.x == 937 || RNor.x == 937 || RWes.x == 937))
						{
							param_1 = "";
						}
						REas.setLocation(98,609);
					}
				}
				
				if(lastTouched.equals("RWes"))
				{
					if(RWes.x > 895 && RWes.x < 1030 && RWes.y > 635 && RWes.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(937,675);
						param_1 = "RWes";
					}
					else if(RWes.x > 1030 && RWes.x < 1120 && RWes.y > 635 && RWes.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(937,675);
						param_1 = "RWes";
					}
					else
					{
						if(!(RSou.x == 937 || REas.x == 937 || RNor.x == 937))
						{
							param_1 = "";
						}
						RWes.setLocation(264,609);
					}
				}
			}
			
			// Flip Function
			
			if(func_call.equals("flip"))
			{
				no1.setLocation(15,438);
				no2.setLocation(98,438);
				no3.setLocation(181,438);
				no4.setLocation(264,438);
			
				no5.setLocation(15,523);
				no6.setLocation(98,523);
				no7.setLocation(181,523);
				
				RNor.setLocation(15,609);
				RSou.setLocation(98,609);
				REas.setLocation(181,609);
				RWes.setLocation(264,609);
				
				param_2 = "";
				
				if(!(param_1.equals("Rleft") || param_1.equals("Rright")))
				{
					param_1 = "";
				}
				
				if(lastTouched.equals("Rleft"))
				{
					if(Rleft.x > 895 && Rleft.x < 1030 && Rleft.y > 635 && Rleft.y < 705)
					{
						Rleft.setLocation(937,675);
						Rright.setLocation(98,695);
						param_1 = "Rleft";
					}
					else if(Rleft.x > 1030 && Rleft.x < 1120 && Rleft.y > 635 && Rleft.y < 705)
					{
						Rleft.setLocation(937,675);
						Rright.setLocation(98,695);
						param_1 = "Rleft";
					}
					else
					{
						if(!(Rright.x == 937))
						{
							param_1 = "";
						}
						Rleft.setLocation(15,695);
					}
				}
				
				if(lastTouched.equals("Rright"))
				{
					if(Rright.x > 895 && Rright.x < 1030 && Rright.y > 635 && Rright.y < 705)
					{
						Rleft.setLocation(15,695);
						Rright.setLocation(937,675);
						param_1 = "Rright";
					}
					else if(Rright.x > 1030 && Rright.x < 1120 && Rright.y > 635 && Rright.y < 705)
					{
						Rleft.setLocation(15,695);
						Rright.setLocation(937,675);
						param_1 = "Rright";
					}
					else
					{
						if(!(Rleft.x == 937))
						{
							param_1 = "";
						}
						Rright.setLocation(98,695);
					}
				}
			}
			
			// Move Function
			
			if(func_call.equals("move"))
			{
				Rleft.setLocation(15,695);
				Rright.setLocation(98,695);
				
				if(param_1.equals("RNor") || param_1.equals("RSou") || param_1.equals("REas") || 
				param_1.equals("RWes"))
				{
					param_2 = param_1;
				}
				else if(param_1.equals("Rleft") || param_1.equals("Rright"))
				{
					param_1 = "";
				}
				
				if(lastTouched.equals("RNor"))
				{
					if(RNor.x > 895 && RNor.x < 1030 && RNor.y > 635 && RNor.y < 705)
					{
						RNor.setLocation(1019,675);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_2 = "RNor";
					}
					else if(RNor.x > 1030 && RNor.x < 1120 && RNor.y > 635 && RNor.y < 705)
					{
						RNor.setLocation(1019,675);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_2 = "RNor";
					}
					else
					{
						if(!(RSou.x == 1019 || REas.x == 1019 || RWes.x == 1019))
						{
							param_2 = "";
						}
						RNor.setLocation(15,609);
					}
				}
				
				if(lastTouched.equals("RSou"))
				{
					if(RSou.x > 895 && RSou.x < 1030 && RSou.y > 635 && RSou.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(1019,675);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_2 = "RSou";
					}
					else if(RSou.x > 1030 && RSou.x < 1120 && RSou.y > 635 && RSou.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(1019,675);
						REas.setLocation(181,609);
						RWes.setLocation(264,609);
						param_2 = "RSou";
					}
					else
					{
						if(!(RNor.x == 1019 || REas.x == 1019 || RWes.x == 1019))
						{
							param_2 = "";
						}
						RSou.setLocation(98,609);
					}
				}
				
				if(lastTouched.equals("REas"))
				{
					if(REas.x > 895 && REas.x < 1030 && REas.y > 635 && REas.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(1019,675);
						RWes.setLocation(264,609);
						param_2 = "REas";
					}
					else if(REas.x > 1030 && REas.x < 1120 && REas.y > 635 && REas.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(1019,675);
						RWes.setLocation(264,609);
						param_2 = "REas";
					}
					else
					{
						if(!(RSou.x == 1019 || RNor.x == 1019 || RWes.x == 1019))
						{
							param_2 = "";
						}
						REas.setLocation(181,609);
					}
				}
				
				if(lastTouched.equals("RWes"))
				{
					if(RWes.x > 895 && RWes.x < 1030 && RWes.y > 635 && RWes.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(1019,675);
						param_2 = "RWes";
					}
					else if(RWes.x > 1030 && RWes.x < 1120 && RWes.y > 635 && RWes.y < 705)
					{
						RNor.setLocation(15,609);
						RSou.setLocation(98,609);
						REas.setLocation(181,609);
						RWes.setLocation(1019,675);
						param_2 = "RWes";
					}
					else
					{
						if(!(RSou.x == 1019 || REas.x == 1019 || RNor.x == 1019))
						{
							param_2 = "";
						}
						RWes.setLocation(264,609);
					}
				}
				
				// ***
				if(lastTouched.equals("no1"))
				{
					if(no1.x > 895 && no1.x < 1030 && no1.y > 635 && no1.y < 705)
					{
						no1.setLocation(937,675);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no1";
					}
					else if(no1.x > 1030 && no1.x < 1120 && no1.y > 635 && no1.y < 705)
					{
						no1.setLocation(937,675);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no1";
					}
					else
					{
						if(!(no2.x == 937 || no3.x == 937 || no4.x == 937 || no5.x == 937 || 
							no6.x == 937 || no7.x == 937))
						{
							param_1 = "";
						}
						no1.setLocation(15,438);
					}
				}
				if(lastTouched.equals("no2"))
				{
					if(no2.x > 895 && no2.x < 1030 && no2.y > 635 && no2.y < 705)
					{
						no2.setLocation(937,675);
						no1.setLocation(15,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no2";
					}
					else if(no2.x > 1030 && no2.x < 1120 && no2.y > 635 && no2.y < 705)
					{
						no2.setLocation(937,675);
						no1.setLocation(15,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no2";
					}
					else
					{
						if(!(no1.x == 937 || no3.x == 937 || no4.x == 937 || no5.x == 937 || 
							no6.x == 937 || no7.x == 937))
						{
							param_1 = "";
						}
						no2.setLocation(98,438);
					}
				}
				if(lastTouched.equals("no3"))
				{
					if(no3.x > 895 && no3.x < 1030 && no3.y > 635 && no3.y < 705)
					{
						no3.setLocation(937,675);
						no2.setLocation(98,438);
						no1.setLocation(15,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no3";
					}
					else if(no3.x > 1030 && no3.x < 1120 && no3.y > 635 && no3.y < 705)
					{
						no3.setLocation(937,675);
						no2.setLocation(98,438);
						no1.setLocation(15,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no3";
					}
					else
					{
						if(!(no2.x == 937 || no1.x == 937 || no4.x == 937 || no5.x == 937 || 
							no6.x == 937 || no7.x == 937))
						{
							param_1 = "";
						}
						no3.setLocation(181,438);
					}
				}
				if(lastTouched.equals("no4"))
				{
					if(no4.x > 895 && no4.x < 1030 && no4.y > 635 && no4.y < 705)
					{
						no4.setLocation(937,675);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no1.setLocation(15,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no4";
					}
					else if(no4.x > 1030 && no4.x < 1120 && no4.y > 635 && no4.y < 705)
					{
						no4.setLocation(937,675);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no1.setLocation(15,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no4";
					}
					else
					{
						if(!(no2.x == 937 || no3.x == 937 || no1.x == 937 || no5.x == 937 || 
							no6.x == 937 || no7.x == 937))
						{
							param_1 = "";
						}
						no4.setLocation(264,438);
					}
				}
				if(lastTouched.equals("no5"))
				{
					if(no5.x > 895 && no5.x < 1030 && no5.y > 635 && no5.y < 705)
					{
						no1.setLocation(15,438);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(937,675);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no5";
					}
					else if(no5.x > 1030 && no5.x < 1120 && no5.y > 635 && no5.y < 705)
					{
						no1.setLocation(15,438);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(937,675);
						no6.setLocation(98,523);
						no7.setLocation(181,523);
						param_1 = "no5";
					}
					else
					{
						if(!(no2.x == 937 || no3.x == 937 || no4.x == 937 || no1.x == 937 || 
							no6.x == 937 || no7.x == 937))
						{
							param_1 = "";
						}
						no5.setLocation(15,523);
					}
				}
				if(lastTouched.equals("no6"))
				{
					if(no6.x > 895 && no6.x < 1030 && no6.y > 635 && no6.y < 705)
					{
						no1.setLocation(15,438);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(937,675);
						no7.setLocation(181,523);
						param_1 = "no6";
					}
					else if(no6.x > 1030 && no6.x < 1120 && no6.y > 635 && no6.y < 705)
					{
						no1.setLocation(15,438);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(937,675);
						no7.setLocation(181,523);
						param_1 = "no6";
					}
					else
					{
						if(!(no2.x == 937 || no3.x == 937 || no4.x == 937 || no5.x == 937 || 
							no1.x == 937 || no7.x == 937))
						{
							param_1 = "";
						}
						no6.setLocation(98,523);
					}
				}
				if(lastTouched.equals("no7"))
				{
					if(no7.x > 895 && no7.x < 1030 && no7.y > 635 && no7.y < 705)
					{
						no1.setLocation(15,438);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(937,675);
						param_1 = "no7";
					}
					else if(no7.x > 1030 && no7.x < 1120 && no7.y > 635 && no7.y < 705)
					{
						no1.setLocation(15,438);
						no2.setLocation(98,438);
						no3.setLocation(181,438);
						no4.setLocation(264,438);
						no5.setLocation(15,523);
						no6.setLocation(98,523);
						no7.setLocation(937,675);
						param_1 = "no7";
					}
					else
					{
						if(!(no2.x == 937 || no3.x == 937 || no4.x == 937 || no5.x == 937 || 
							no6.x == 937 || no1.x == 937))
						{
							param_1 = "";
						}
						no7.setLocation(181,523);
					}
				}
				// ***
				
			}
			
			// Outputs
			
			System.out.println("Param1: "+param_1);
			System.out.println("Param2: "+param_2);
        }

        public void updateLocation(MouseEvent e) {
			// Object Function
			if(lastTouched.equals("rect"))
			{
				rect.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("rect2"))
			{
				rect2.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("rect3"))
			{
				rect3.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			// Drag Function
			if(lastTouched.equals("dragF1"))
			{
				dragF1.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("dragF2"))
			{
				dragF2.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("dragF3"))
			{
				dragF3.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("dragF4"))
			{
				dragF4.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			// Magnitude
			if(lastTouched.equals("no1"))
			{
				no1.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("no2"))
			{
				no2.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("no3"))
			{
				no3.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("no4"))
			{
				no4.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("no5"))
			{
				no5.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("no6"))
			{
				no6.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("no7"))
			{
				no7.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			// Direction
			if(lastTouched.equals("RNor"))
			{
				RNor.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("RSou"))
			{
				RSou.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("REas"))
			{
				REas.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("RWes"))
			{
				RWes.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			// Mirror
			if(lastTouched.equals("Rleft"))
			{
				Rleft.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
			if(lastTouched.equals("Rright"))
			{
				Rright.setLocation(preX + e.getX(), preY + e.getY());
				repaint();
			}
        }
    }
}
