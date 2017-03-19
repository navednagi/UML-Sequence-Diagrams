

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
 
public class btn1 extends JPanel{
	private JButton jcomp1;
	private JButton jcomp2;
	private JButton jcomp3;
	private JButton jcomp4;
	private JButton jcomp5;
	private JButton jcomp7;
	private JLabel jcomp6;
	private JTextArea jcomp8;
	private JButton jcomp9;
	private JScrollPane jcomp10;
	private JTextArea jcomp11;
	private JScrollPane scrollPane;
	private File file;
	private JTextArea jcomp12;
	static int cout=0;
	static String sg[]=new String[1000];
	String pc[]=new String[1000];
	int count=0;
	String sloop[]=new String[100];
	int cloop=0;
	String gnrits[]=new String[1000];
	int cout11=0;
	private JButton jcomp19;
	static String scen[]=new String[1000];
	static int csc=0;
	String vst[][]=new String[100][3];
	String test11= JOptionPane.showInputDialog("Enter directory containing files");
	File folder = new File(test11);
	File[] listOfFiles = folder.listFiles();
	JComboBox[][] jcb=new JComboBox[1000][listOfFiles.length];
	//private JTextArea jcomp13=new JTextArea();
	//private JTextArea jcomp14=new JTextArea();
	//private JTextArea jcomp15=new JTextArea();     
	public btn1(){
	    jcomp2 = new JButton ("Step-I");
	    jcomp3 = new JButton ("Step-II");
	    jcomp4 = new JButton ("Step-III");
	    jcomp5 = new JButton ("Step-IV");
	    jcomp1 = new JButton ("Home");
	    jcomp7 = new JButton ("Browse");
	    jcomp6 = new JLabel ("Select Use-Case Description File");
	    jcomp8 = new JTextArea();
	    jcomp10 = new JScrollPane(jcomp8);
	    jcomp9 = new JButton("Proceed");
	    jcomp11=new JTextArea();
	    jcomp12=new JTextArea();
	    scrollPane = new JScrollPane(jcomp8);
	    jcomp19=new JButton("Proceed to 1.b");
	    setPreferredSize (new Dimension (1000, 700));
	    setLayout (null);

	    add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);

	    jcomp1.setBounds (0, 10, 100, 25);
	    jcomp2.setBounds (125, 10, 100, 25);
	    jcomp3.setBounds (250, 10, 100, 25);
	    jcomp4.setBounds (375, 10, 100, 25);
	    jcomp5.setBounds (500, 10, 100, 25);
	    
	    
	    
	    jcomp1.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    selectionButtonPressed();
	    		  } 
	    		} );
	}
	
	public void selectionButtonPressed(){
		removeAll();
		revalidate();
		repaint();
		add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);
		jcomp1.setBackground(new Color(59, 89, 182));
		add (jcomp6);
	    add (jcomp7);
	    add (scrollPane);
	    add (jcomp9);
	    add (jcomp10);
	    add (jcomp11);
	    add (jcomp12);
	    jcomp6.setBounds (0, 50, 250, 25);
	    jcomp7.setBounds(470, 50, 100, 25);
	    scrollPane.setBounds(0, 100, 800, 300);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jcomp9.setBounds(700, 670, 100, 25);
	    jcomp11.setBounds(250, 50, 200, 25);
	    jcomp12.setBounds(0, 450, 800, 200);
	    jcomp7.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    try {
						selectionButtonPressed1();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	    		  } 
	    		} );
	    jcomp9.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    try {
						selectionButtonPressed2();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	    		  } 
	    		} );	    
	}
	
	public void selectionButtonPressed1() throws Exception{
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(chooser);
		file = chooser.getSelectedFile();
		jcomp11.append(file.getName());
		jcomp11.setEditable(false);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();
		while(line != null){
		 jcomp8.append(line + "\n");
		  line = in.readLine();
		}
	}
	public void selectionButtonPressed2() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(file));
		Stack<String> ifstack = new Stack<String>();
		Stack<String> whilestack=new Stack<String>();
		String line1 = in.readLine();
		int check=0;
		int lc=0;
		while(line1 != null){
			String line= line1.toUpperCase();
			lc++;
			if(line.contains("IF")&&!line.contains("END"))
			{
				ifstack.push("if");
				}
			if(line.contains("WHILE")&&!line.contains("END"))
			{
				whilestack.push("while");
			}
			if(line.contains("ENDIF"))
			{
				if(ifstack.empty())
				{
					jcomp12.append("EndIf without If in line+"+lc+"\n");
					check=1;
				}
				else
				 ifstack.pop();
			}
			if(line.contains("ENDWHILE"))
			{
				if(whilestack.empty())
				{
					jcomp12.append("EndWhile without While in line+"+lc+"\n");
					check=1;
				}
				else
				whilestack.pop();
			}
			if(line.contains("ELSE"))
			{
			    if(ifstack.empty())
			    {
			    	jcomp12.append("Else Without If in line+"+lc+"\n");
			    	check=1;
			    }
			}
			if(line.contains("THEN")&&(!(line.contains("IF"))))
			{
				jcomp12.append("Then without If in line+"+lc+"\n");
				check=1;
			}
			line1 = in.readLine();
		}
		if(!ifstack.empty())
		{
			jcomp12.append("Unclosed If \n");
			check=1;
		}
		if(!whilestack.empty())
		{
			jcomp12.append("Unclosed While \n");
			check=1;
		}
		else if(check==0)
		{
			jcomp12.append("No errors \n");
		}
		jcomp2.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    try {
	    		    	selectionButtonPressed3();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	    		  } 
	    		} );
	}
	public void selectionButtonPressed3(){
		removeAll();
		revalidate();
		repaint();
		add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);
	    jcomp1.setBackground(null);
		jcomp2.setBackground(new Color(59, 89, 182));
		
		int linecount=0;
		try{
		String aname[]=new String[1000];
		for(int v=0;v<=999;v++)
		{
			aname[v]="";
		}  
		BufferedReader in = new BufferedReader(new FileReader(file));
		Stack<String> ifstack = new Stack<String>();
		Stack<String> whilestack=new Stack<String>();
		Stack<String> whilestack1=new Stack<String>();
		String line1 = in.readLine();
		JTextArea jcomp13=new JTextArea();
		JTextArea jcomp14=new JTextArea();
		JTextArea jcomp15=new JTextArea();
		jcomp15.setEditable(false);
		JScrollPane j1=new JScrollPane(jcomp13);
		JScrollPane j2=new JScrollPane(jcomp14);
		JScrollPane j3=new JScrollPane(jcomp15);
		j1.setBounds(50, 80, 80, 40);
	    j1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    j2.setBounds(130, 80, 150, 40);
	    j2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    j3.setBounds(280, 80, 400, 40);
	    j3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    int c1=1;
		char ch='A';
		int vc=0;
		
		for(int jlp=0;jlp<listOfFiles.length;jlp++)
		{
			JLabel jl=new JLabel();
			jl.setText((listOfFiles[jlp].getName()).replaceFirst("[.][^.]+$", ""));
			jl.setBounds(680+(jlp*80),45,80,30);
			add(jl);
		}
		
		String stu[][]=new String[listOfFiles.length][20];
		for(int jlp=0;jlp<listOfFiles.length;jlp++)
		{
			BufferedReader in7 = new BufferedReader(new FileReader(listOfFiles[jlp]));
			int cct=0;
			String liner=in7.readLine();
			while(liner!=null){
				stu[jlp][cct++]=liner;
				liner=in7.readLine();
			}
		}
	
		
			while(line1 != null){
			vst[vc][0]=line1;
			vst[vc][2]="S"+(vc+1);
			if(!line1.contains("ELSE")&&!line1.contains("ENDIF")&&!line1.contains("ENDWHILE"))
			{
			if(c1<=9)
			{
			aname[count]+=ch;
			aname[count++]+=c1++;
			}
			else
			{
				c1=1;
				ch++;
				aname[count]+=ch;
				aname[count++]+=c1++;
			}
			vst[vc][1]=aname[count-1];
			}
			else{
				count++;
                vst[vc][1]="";			
			}
			vc++;
			line1=in.readLine();
		}
			count=1;
			pc[0]+='-';
			BufferedReader in1 = new BufferedReader(new FileReader(file));
			add(j1);
		    add(j2);
		    add(j3);
			jcomp13.append(aname[0].replace("null", ""));
			String sdad=in1.readLine();
			jcomp14.append((sdad.replace("null", "")));
			jcomp15.append((pc[0].replace("null", "")));
			linecount++;
			String line2 = in1.readLine();
			int elsef=0,f=0,w=0,cloud=1;
			
			if((sdad.contains("WHILE"))&&count==1){
				w=1;
				whilestack.push(aname[0]+"(F)");
				whilestack1.push("");
				sloop[cloop++]=aname[count-1];
			}
			else if(sdad.contains("IF"))
				{
				f=1;
				ifstack.push(aname[0]+"(F)");
				}
			while(line2!=null){
				if(line2.contains("IF")&&!line2.contains("END")&&f==0){
					f=1;
					if(count-1>=0)
					{
					if(w==0){	
					pc[count++]+=pc[count-2]+","+aname[count-2];
					ifstack.push(pc[count-1]+","+aname[count-1]+"(F)");
					}
					else
					{	
						pc[count++]+=pc[count-2]+","+aname[count-2]+"(T)";
						ifstack.push(pc[count-1]+","+aname[count-1]+"(F)");
						w=0;
						}
					}
					else{
						count++;
					ifstack.push(aname[count]+"(F)");
					}
				}
				else if(line2.contains("ELSE")){
					elsef=1;
					pc[count++]+=ifstack.pop();
					ifstack.push(pc[count-2]+","+aname[count-2]);
				}
				else if(line2.contains("ENDIF")){
					if(elsef==1){
					pc[count++]+="{"+ifstack.pop()+"+"+pc[count-2]+","+aname[count-2]+"}";
					}
					else
						{
						pc[count++]="{"+pc[count-2]+","+aname[count-2]+"+"+ifstack.pop()+"}";
						}
					elsef=0;
					f=0;
				}
				else if(f==1)
				{
					if(line2.contains("IF")&&!line2.contains("END")){
					f=1;
					pc[count++]+=pc[count-2]+","+aname[count-2]+"(T)";
					ifstack.push(pc[count-1]+","+aname[count-1]+"(F)");
					}
					else if(line2.contains("WHILE")&&!line2.contains("END")&&w==0){
						w=1;
						sloop[cloop++]=aname[count];
						pc[count++]+=pc[count-2]+","+aname[count-2]+"(T)";
						whilestack.push(pc[count-1]+","+aname[count-1]+"(F)");
						whilestack1.push(aname[count-1]+"(F)");
						f=0;
					}
					else
					{	pc[count++]+=pc[count-2]+","+aname[count-2]+"(T)";
					f=0;
					}
					
				}
				else if(line2.contains("WHILE")&&!line2.contains("END")&&w==0){
					w=1;
					sloop[cloop++]=aname[count];
					if(count-1>=0)
					{
					pc[count++]+=pc[count-2]+","+aname[count-2];
					whilestack.push(pc[count-1]+","+aname[count-1]+"(F)");
					whilestack1.push(aname[count-1]+"(F)");
					}
					else{
						count++;
					whilestack.push(aname[count]+"(F)");
					}
				}
				else if(line2.contains("ENDWHILE")){
					String so=whilestack.pop();
					String si=whilestack1.pop();
					pc[count++]+="["+so+"+"+pc[count-2]+","+aname[count-2]+","+si+"]";
					w=0;
				}
				else if(line2.contains("EXIT"))
				{
					pc[count++]+=pc[count-2]+","+aname[count-2]+",$";
				}
				else if(w==1)
				{
					pc[count++]+=pc[count-2]+","+aname[count-2]+"(T)";
					w=0;
				}
				else
				{
					if(count-2>=0)
				pc[count++]+=pc[count-2]+","+aname[count-2];
					else
						pc[count++]+=aname[count-2];
				}
				JLabel jcomp199=new JLabel("Master Action Sequence Table");
				JLabel jcomp19=new JLabel("Action No.");
				JLabel jcomp20=new JLabel("Action Name");
				JLabel jcomp21=new JLabel("Precondition");
				//JLabel jcomp23=new JLabel("Physical State");
				//JLabel jcomp22=new JLabel("Virtual State");
				jcomp199.setBounds(350,40,800,15);
				jcomp19.setBounds(50,45,80,30);
				jcomp20.setBounds(130,45,150,30);
				jcomp21.setBounds(280,45,400,30);
				//jcomp22.setBounds(680,45,80,30);
				//jcomp23.setBounds(760,45,80,30);

				add(jcomp199);
				add(jcomp19);
				add(jcomp20);
				add(jcomp21);
				//add(jcomp22);
				//add(jcomp23);
				//System.out.print((aname[count-1]+"              "+pc[count-1]+"              "+line2).replace("null",""));
				JTextArea jcomp16=new JTextArea();
				JTextArea jcomp17=new JTextArea();
				JTextArea jcomp18=new JTextArea();
				
				JScrollPane j4=new JScrollPane(jcomp16);
				JScrollPane j5=new JScrollPane(jcomp17);
				JScrollPane j6=new JScrollPane(jcomp18);
				/*JTextArea jcomp24=new JTextArea();
				JScrollP0ane j7=new JScrollPane(jcomp24);
				JTextArea jcomp25=new JTextArea();
				JScrollPane j8=new JScrollPane(jcomp25);
				*/
				
				for(int jlp=0;jlp<listOfFiles.length;jlp++)
				{
					jcb[linecount-1][jlp]=new JComboBox(stu[jlp]);
					jcb[linecount-1][jlp].setBounds(680+(jlp*80),40+(linecount*40),80,40);
					add(jcb[linecount-1][jlp]);
				}
				j4.setBounds(50, 80+(linecount*40), 80, 40);
			    j4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			    j4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			    j5.setBounds(130, 80+(linecount*40), 150, 40);
			    j5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			    j5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			    j6.setBounds(280, 80+(linecount*40), 400, 40);
			    j6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			    j6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			    /*j7.setBounds(680, 80+((linecount-1)*40), 80, 40);
			    j7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			    j7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			    j8.setBounds(760, 80+((linecount-1)*40), 80, 40);
			    j8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			    j8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			    */add(j4);
			    add(j6);
			    add(j5);
			    //add(j7);
			    //add(j8);
			    jcomp16.append((aname[count-1]));
				jcomp17.append((line2.replace("null", "")));
				jcomp18.append((((pc[count-1].replace("null", ""))).replace(",,",",")).replace("-,",""));
				//jcomp24.append(vst[cloud++-1][2]);
				linecount++;
				line2=in1.readLine();
			}
			add(jcomp19);
			jcomp19.setBounds(1050,650,150 , 25);
			jcomp19.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    		    try {
							selectionButtonPressed4();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
		    		  } 
		    		} );
		}catch(Exception e){}
	}
	public void selectionButtonPressed4()throws Exception{
		removeAll();
		revalidate();
		repaint();
		add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);
	    JLabel jl=new JLabel("1b.Action Sequences of All Scenarios");
	    add(jl);
	    jl.setBounds(0, 40, 300, 60);
	    jcomp1.setBounds (0, 10, 100, 25);
	    jcomp2.setBounds (100, 10, 100, 25);
	    jcomp3.setBounds (200, 10, 100, 25);
	    jcomp4.setBounds (300, 10, 100, 25);
	    jcomp5.setBounds (200*2, 10, 100, 25);
            String s =pc[count-1].replace("null", "").replace(",", "").replace("-","");
            caller(s);
            String sf[]=new String[cout];
		for(int o=0;o<cout;o++)
			System.out.println(sf[o]=sg[o].replace(",",""));
		String srep1[]=new String[100];
		int srcout=0;
		int stindex,lsindex;
		int gstindex=0,glsindex=0,go=0;
		System.out.println(sloop[cloop-1]);
		for(int o=0;o<cout;o++)
		{
                        for(int oo=0;oo<cloop;oo++)
                        {stindex=sf[o].indexOf(sloop[oo]+"(T)")+5;
			lsindex=sf[o].indexOf(sloop[oo]+"(F)");
			if(stindex<lsindex&&stindex!=4&&lsindex!=-1){
				srep1[srcout++]=sf[o].substring(stindex,lsindex);
				gstindex=stindex;
				glsindex=lsindex;
				go=o;
			}
                        }
        }
		int pee;
		for(pee=0;pee<100;pee++)
			{
			if(srep1[pee]==null)
			{
			break;
			}
			}
		String srep[]=new String[pee];
		for(int pss=0;pss<pee;pss++)
		{
			srep[pss]=srep1[pss];
		}
		Scanner sc=new Scanner(System.in);
		
		String test1= JOptionPane.showInputDialog("Enter no. of repetition for loop in "+sloop[0]);
		int k =  Integer.parseInt(test1);
	        printAllKLength(srep, k);		
	        for(int ppp=0;ppp<csc;ppp++)
	        {
	        	String temps=sf[go].substring(0,gstindex)+scen[ppp]+sf[go].substring(glsindex, sf[go].length());
	        	String temps1=new String();
	        	for(int qe=temps.length()-1;qe>=0;qe--)
	        	{
	        		if(temps.charAt(qe)=='}'&&temps.charAt(qe-1)!='{')
	        		{
	        			temps1+=")T("+(new StringBuffer(sloop[0]).reverse()).toString();
	        		}
	        		else if(temps.charAt(qe)=='{')
	        		{}
	        		else if(temps.charAt(qe)=='}'&&temps.charAt(qe-1)=='{')
	        		{}
	        		else
	        			temps1+=temps.charAt(qe);
	        	}
	        	StringBuffer sbsc;
	        	System.out.println(sbsc=new StringBuffer(temps1).reverse());
	        	int uy=sbsc.indexOf(sloop[0]+"(T)");
	        	int le=sbsc.length();
	        	int lco=1,endwf=1;
	        	for(int lk=uy+5;lk<le;lk++)
	        	{
	        		if(lk+5<le&&(sbsc.toString().substring(lk, lk+5)).equals(sloop[0]+"(T)"))
	        		{
	        			lco++;
	        			lk+=5;
	        			endwf=1;
	        		}
	        		else if(sbsc.toString().substring(lk-4,lk+1).equals(sloop[0]+"(F)"))
	        		{
	        			endwf=0;
	        		}
	        		else if(((sbsc.toString().charAt(lk)>='0'&&sbsc.toString().charAt(lk)<='9')||(sbsc.toString().charAt(lk)==')'&&!sbsc.toString().substring(lk-4,lk+1).equals(sloop[0]+"(F)")))&&sbsc.toString().charAt(lk+1)!='('&&endwf!=0)
	        		{
	        		sbsc.insert(++lk,"-"+lco);
	        		lk++;
	        		le+=2;
	        		}
	        	}
	        	for(int yt=1;yt<le;yt++)
	        	{
	        		if(sbsc.toString().charAt(yt)>='A'&&sbsc.toString().charAt(yt)<='Z'&&sbsc.toString().charAt(yt)!='T'&&sbsc.toString().charAt(yt)!='F')
	        				{
	        			sbsc.insert(yt++, ',');
	        			le++;
	        				}
	        	}
	        	JTextArea jtxt=new JTextArea();
	        	JScrollPane jsc=new JScrollPane(jtxt);
			    jsc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			    jsc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        	jsc.setBounds(0,100+ppp*45,500,40);
	        	add(jsc);
	        	jtxt.append("Scenario "+(ppp+1)+"      "+sbsc.toString());
	        	gnrits[cout11++]=sbsc.toString();
	        }
	        JFrame frame=new JFrame("Action Sequence Scenarios");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel jp=new JPanel();
			jp.setPreferredSize(new Dimension(500,500));
			jp.setLayout(null);
			frame.add(jp);
			frame.pack();
			frame.setVisible(true); 
	        String tabcol[] = new String[2];
	        tabcol[0]="Scenario No.";
	        tabcol[1]="Scenario Action Sequence";
	        String gnrits1[][] = new String[cout11+1][2];
	        int gnloop;
	        for( gnloop=0;gnloop<cout11;gnloop++){
	        	gnrits1[gnloop][0]="Action Sequence for Scenario "+(gnloop+1);
	        	gnrits1[gnloop][1]=gnrits[gnloop];
	        }
	        gnrits1[gnloop][0]="No. of Scenario";
	        gnrits1[gnloop][1]=""+gnloop;
	   
	        JTable table1 =new JTable(gnrits1,tabcol);
	        table1.setBounds(30, 40, 800, 500);
	        frame.add(new JScrollPane(table1));
	        jcomp3.addActionListener(new ActionListener() { 
		    	  public void actionPerformed(ActionEvent e) { 
		    		    try {
		    		    	selectionButtonPressed5();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
		    		  } 
		    		} );
	}
	public void selectionButtonPressed5()
	{
		jcomp3.setBackground(new Color(59, 89, 182));
		jcomp2.setBackground(null);
		for(int ty=0;ty<cout11;ty++)
			hazard(gnrits[ty]);
	}
        public static void caller(String s) throws Exception
	{
		if(s.contains("+"))
		{
			String sr[]=brek(s);
			if(sr[0].contains("+"))
			{
				caller(sr[0]);
			}
			else
				{
				sg[cout++]=sr[0];
				}
			if(sr[1].contains("+"))
			{
				caller(sr[1]);
			}
			else
			{
				sg[cout++]=sr[1];
			}
		}	
	}
        static void printAllKLength(String set[], int k) {
        int n = set.length;        
        printAllKLengthRec(set, "", n, k);
    }
 
    static void printAllKLengthRec(String set[], String prefix, int n, int k) {
         
        if (k == 0) {
            System.out.println(prefix);
            scen[csc++]=prefix;
            return;
        }
 
        for (int i = 0; i < n; ++i) {
             
            String newPrefix=new String();
        	newPrefix = "{"+prefix +"}" + set[i]; 
            printAllKLengthRec(set, newPrefix, n, k - 1); 
        }
    }
	public static String[] brek(String s) throws Exception
	{
		System.out.println(s);
		int cflag=0,sflag=0;
		int n=s.length();
		int i,j,k;
		for(i=n-1;i>=0;i--)
		{
			if(s.charAt(i)=='}')
			{
				cflag++;
				break;
			}
			else if(s.charAt(i)==']')
			{
				sflag++;
				break;
			}
		}
		int sflag1=0,cflag1=0;
		for(j=i-1;j>=0;j--)
		{
				if(s.charAt(j)=='+'&&sflag1==0&&cflag1==0)
				{
					break;
				}
				else if(s.charAt(j)=='}')
				{
					cflag1++;
				}
				else if(s.charAt(j)=='{')
				{
					cflag1--;
				}
				else if(s.charAt(j)==']')
				{
					sflag1++;
				}
				else if(s.charAt(j)=='[')
				{
					sflag1--;
				}
		}
		for(k=j-1;k>=0;k--)
		{
			if(cflag>0)
			{
				if(s.charAt(k)=='{')
					break;
			}
			else if(sflag>0)
			{
				if(s.charAt(k)=='[')
					break;
			}
		}
		String sr[]=new String[2];
		sr[0]=s.substring(k+1, j)+","+s.substring(i+1,n);
		sr[1]=s.substring(j+1, i)+","+s.substring(i+1,n);
		return sr;	
	}
	
	/*public void hazard(String ss){
		//System.out.println(ss);
		JFrame frame=new JFrame("Scene");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel jp=new JPanel();
		jp.setPreferredSize(new Dimension(500,500));
		jp.setLayout(null);
		frame.add(jp);
		frame.pack();
		frame.setVisible(true);
		StringTokenizer st=new StringTokenizer(ss);
		int linecount=0;
		String zz[]=new String[100];
		JLabel jl1=new JLabel("Action name");
		JLabel jl2=new JLabel("Virtual State");
		JLabel jl3=new JLabel("Hazard State");
		jl1.setBounds(50,40,80,40);
		jl2.setBounds(130,40,150,40);
		jl3.setBounds(280,40,400,40);
		jp.add(jl1);
		jp.add(jl3);
		jp.add(jl2);
		for(int wee=0;wee<100;wee++)
			zz[wee]="";
		while(st.hasMoreTokens()){
			JTextArea jt1=new JTextArea();
			JTextArea jt2=new JTextArea();
			JTextArea jt3=new JTextArea();
			JScrollPane jt4=new JScrollPane(jt1);
			JScrollPane jt5=new JScrollPane(jt2);
			JScrollPane jt6=new JScrollPane(jt3);
			jt4.setBounds(50, 80+(linecount*40), 80, 40);
		    jt4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		    jt4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    jt5.setBounds(130, 80+(linecount*40), 150, 40);
		    jt5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		    jt5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    jt6.setBounds(280, 80+(linecount*40), 400, 40);
		    jt6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		    jt6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    jp.add(jt4);
		    jp.add(jt6);
		    jp.add(jt5);
		    String st2=st.nextToken(",");
		    for(int j=0;j<100;j++){
		    	if(vst[j][1]!=null){
		    		if(vst[j][1].equals(st2.substring(0,2)))
		    		{
		    			
		    			jt1.append(vst[j][1]);
			    		jt2.append(vst[j][2]);	
		    		
		    		jt1.append(vst[j][1]);
		    		jt2.append(vst[j][2]);
		    		if(linecount==0)
		    		{
		    			zz[linecount]=",!"+vst[j][2]+",";
		    			jt3.append(zz[linecount++]);
		    			break;
		    		}
		    		else{
		    			StringTokenizer st3=new StringTokenizer(zz[linecount-1]);
		    			System.out.println(zz[linecount-1]);
		    			while(st3.hasMoreTokens()){
		    				String st4=st3.nextToken(",");
		    				System.out.println(st4);
		    				zz[linecount]+=st4+"^"+vst[j][2]+",   ";
		    				zz[linecount]+=st4+"^!"+vst[j][2]+",   ";
		    			}
		    			zz[linecount]+="!"+vst[j][2]+",";
		    			jt3.append(zz[linecount++]);
		    			
		    			break;
		    		}
		    		//System.out.println(vst[j][0]);
		    		}
		    		}
		   }
		   
		}
	}*/
	private int astfs=0;
	public void hazard(String ss)
	{	
		System.out.println(ss+"+++++++++++++++");
		JFrame frame=new JFrame("Action Sequence State Transition for Scenario "+(astfs+++1));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel jp=new JPanel();
		jp.setPreferredSize(new Dimension(500,500));
		jp.setLayout(null);
		//frame.add(jp);
		frame.pack();
		frame.setVisible(true);
		JTextArea jta=new JTextArea();
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setBounds(40, 40, 800, 500);
	    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //jp.add(jsp);
	    
	    String columns[]=new String[listOfFiles.length+2];
	    columns[listOfFiles.length+1]="Hazard States";
	    int colcount=0;
	    String data[][]=new String[100][listOfFiles.length+2];
	    
	    	data[0][listOfFiles.length+1]="-";
	    
	    int datacol=0;
	    int datarow=0;

	    columns[colcount++]="Action no.";
	    for(int jlp=0;jlp<listOfFiles.length;jlp++)
		{
	    	columns[colcount++]=listOfFiles[jlp].getName().replaceFirst("[.][^.]+$", "");
		}
		StringTokenizer stt=new StringTokenizer(ss,",");
		while(stt.hasMoreTokens()){
			String sttt=stt.nextToken();
			data[datarow][0]=sttt;
			for(int jlp=0;jlp<listOfFiles.length;jlp++)
			{
				data[datarow][jlp+1]=((String)(jcb[sttt.charAt(1)-'1'][jlp]).getSelectedItem())+sttt.substring(2, sttt.length()).replace("(T)","").replace("(F)", "").replace("$", "");
			}
			datarow++;
		}
		int firstoc=0;
		for(int i=1;i<datarow;i++)
	    {

			int changecol[]=new int[listOfFiles.length+1];
			int changecolcount=0;
			int changeflag=0;
			for(int j=1;j<=listOfFiles.length;j++)
		    {
				if(!(data[i][j].equals(data[i-1][j])))
					{
					changeflag=1;
					changecol[changecolcount++]=j;
					
					}
			}
			if(changeflag==1 && firstoc==0)
			{
						for(int j=0;j<changecolcount;j++)
					    {
						//data[i][listOfFiles.length+1]+="! ("+data[i][changecol[j]]+")   ,   ";
							data[i][listOfFiles.length+1]=
									"";
							data[i][listOfFiles.length+1]+=
									""+columns[changecol[j]]+"!="+data[i][changecol[j]]+" ";
						}
						firstoc=1;
			}
			else if(changeflag==1)
			{
				data[i][listOfFiles.length+1]="";
				String sss2=new String();
				String sss3=new String();
				
				for(int j=0;j<changecolcount;j++)
				    {
					//data[i][listOfFiles.length+1]+="! ("+data[i][changecol[j]]+")   ,   ";
							sss2+=columns[changecol[j]]+"!="+data[i][changecol[j]]+",";
					}
						
				for(int k=i-1;k>0;k--)
					{
						if(!data[k][listOfFiles.length+1].contains("--"))
						{
							StringTokenizer sstt1=new StringTokenizer(sss2,",");
							while(sstt1.hasMoreTokens())
							{
								StringTokenizer sstt=new StringTokenizer(data[k][listOfFiles.length+1],",");
								data[i][listOfFiles.length+1]+=sss2+",";
								String sss4=sstt1.nextToken();
								while(sstt.hasMoreTokens())
								{
									String sss1=sstt.nextToken();
									sss3=sss4+" ^ "+sss1+","+sss4.replace("!", "")+" ^ "+sss1;
									data[i][listOfFiles.length+1]+=sss3+",";
									System.out.println(sss3);
								}
							}
							
							break;
						}
					}
				}
			else
				data[i][listOfFiles.length+1]="----";  
	    }
		
		JTable table = new JTable(data, columns);
		table.setBounds(30,40,800,500);  
		JScrollPane table1=new JScrollPane(table);
		table1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		frame.add(new JScrollPane(table));
		
	}
	public static void main (String[] args){
	JFrame frame = new JFrame("SOFTWARE FAULT TREE ANALYSIS OF USE CASES");
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	btn1 b=new btn1();
	JScrollPane scrollPane = new JScrollPane(b);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.add(scrollPane);
    frame.pack();
    frame.setVisible (true);
	}
}