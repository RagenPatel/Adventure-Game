
public class Movie {
	public static void main(String[] args){


		Start();

	}

	public static void Start(){
		String BG1 = "BG1.png", defaultSprite = "walk.gif", orcLeft = "orcLeft.png", walkRightD = "walkRightDefault.png",
				walkLeftD="walkLeftDefault.png",walkDownD="walkDownDefault.png",walkLeftS="walkLeftShield.png", 
				walkRightS="walkRightShield.png", walkUpS = "walkUpShield.png", walkDownS="walkDownShield.png",
				skeletonDown = "SkeletonDown.png";
		Animation bg1 = new Animation(1920,1080);
		Sprite sprite1 = new Sprite(defaultSprite);
		Sprite orcL = new Sprite(orcLeft);
		Sprite mainLeftD = new Sprite(walkLeftD);
		Sprite mainRightD = new Sprite(walkRightD);
		Sprite mainDownD = new Sprite(walkDownD);

		Sprite mainUpS = new Sprite(walkUpS);
		Sprite mainDownS = new Sprite(walkDownS);
		Sprite mainLeftS = new Sprite(walkLeftS);
		Sprite mainRightS = new Sprite(walkRightS);

		Sprite SkeletonDown = new Sprite(skeletonDown);

		char answer;

		bg1.setBackgroundImage(BG1);
		bg1.setFrameRate(1000);
		bg1.addSprite(sprite1);
		sprite1.setPosition(1475, 0);
		bg1.addSprite(orcL);
		orcL.setPosition(1450, 550);

		bg1.addSprite(SkeletonDown);
		SkeletonDown.setPosition(1780, 860);
		bg1.frameFinished();

		int prevPos = 1450;
		int sprite1Pos;
		int skeletonX = SkeletonDown.getXposition();
		int skeletonY = SkeletonDown.getYposition();


		for(int i=0;i<860;i++){
			sprite1Pos=i%860;
			sprite1.setPosition(1475,sprite1Pos);

			prevPos = orcL.getXposition();
			orcLeft(prevPos,orcL);


			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			if(i == 550){
				System.out.println("Do you want to go (l)eft or (u)p?");
				answer = IO.readChar();
				if(answer == 'l'){
					CharLeft1(mainLeftD, orcL, prevPos, bg1, sprite1, skeletonY, SkeletonDown, mainDownD, mainRightD);
				}
			}

			bg1.frameFinished();


		}
		/*
		mainCharEvent1(mainLeftD, orcL, prevPos,bg1,sprite1,skeletonY, SkeletonDown);

		mainCharEvent2(mainDownD,orcL,prevPos,bg1, mainLeftD, skeletonY, SkeletonDown);

		mainCharEvent3(mainRightD, orcL, prevPos, bg1,mainDownD, skeletonY, SkeletonDown);

		mainCharEvent4(mainLeftS, orcL, prevPos, bg1, mainRightD, skeletonY, SkeletonDown);

		mainCharEvent5(mainUpS, orcL, prevPos, bg1, mainLeftS, skeletonY, SkeletonDown);

		mainCharEvent6(mainLeftS, orcL, prevPos, bg1, mainUpS, skeletonY, SkeletonDown);

		mainCharEvent7(mainDownS, orcL, prevPos, bg1, mainLeftS, skeletonY, SkeletonDown);

		mainCharEvent8(mainLeftS, orcL, prevPos, bg1, mainDownS, skeletonY, SkeletonDown);

		mainCharEvent9(mainUpS, orcL, prevPos, bg1, mainLeftS, skeletonY, SkeletonDown);
		 */

	}
	
	public static void FightOrc(int mainHP, int OrcHP){
		while(OrcHP>0 || mainHP >0){
			System.out.println("(F)ight or (D)efend?");
			int critF = 1, critD=1;
			if(IO.readChar()=='f'||IO.readChar()=='F'){
				critF = (int)Math.random();
				critF*=100;
				critD = (int)Math.random();
				critD*=100;
				if(critF<30){
					System.out.println("Critical strike!");
					OrcHP-=2;
				}else{
					OrcHP--;
				}
				if(critD<20){
					System.out.println("Orc hit a critical strike!");
					mainHP-=2;
				}else{
					mainHP--;
				}
				System.out.println("Your HP: "+mainHP + " Orc HP: "+ OrcHP);
				System.out.println(critF+" orc hit: "+critD);
				
			}else if(IO.readChar()=='d'||IO.readChar()=='D'){
				int crit = (int)Math.random();
				crit*=100;
				if(crit<40){
					System.out.println("Your defense was amazing! Damage negated.");
				}else if(crit<20){
					System.out.println("Orc hit a critical strike!");
					mainHP-=2;
				}else{
					mainHP--;
				}
				
				System.out.println("Your HP: "+mainHP + " Orc HP: "+ OrcHP);
			
				if(OrcHP<=0){
					System.out.println("You defeated the Orc! Teleporting to a safe place!");
					break;
				}
				
			}
			
		}
	}

	public static void orcLeft(int prevPos, Sprite orcL){
		if(prevPos==1150){
			prevPos=1475;
		}else{
			prevPos--;
		}
		orcL.setPosition(prevPos,550);

	}

	public static void skeleton(int getY, Sprite SkeletonDown){

		if(getY==250){
			getY = 860;
		}else{
			getY--;
		}
		SkeletonDown.setPosition(1780,getY);
	}

	
	public static void CharLeft1(Sprite mainLeftD, Sprite orcL, int prevPos, Animation bg1, Sprite sprite1, int skeletonY,Sprite SkeletonDown, Sprite mainDownD, Sprite mainRightD){
		bg1.removeSprite(sprite1);
		bg1.addSprite(mainLeftD);

		int mainCharX=0;
		int mainHP = 20, OrcHP = 5;
		for(int i = 1475;;i--){

			mainLeftD.setPosition(i,550);

			mainCharX= mainLeftD.getXposition();

			prevPos = orcL.getXposition();
			orcLeft(prevPos,orcL);


			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);


			if(mainCharX == orcL.getXposition()){
				mainHP = 20;
				OrcHP = 5;
				FightOrc(mainHP, OrcHP);
			}
			if(i == 1160){
				System.out.println("Would you like to go (d)own or (r)ight?");
				if(IO.readChar()=='d'){
					CharDown1(mainDownD, orcL, prevPos, bg1, mainLeftD, skeletonY,SkeletonDown, mainHP);

				}else if(IO.readChar() == 'r'){
					CharRight1(mainRightD, orcL, prevPos, bg1, mainLeftD, skeletonY,SkeletonDown, mainHP, OrcHP);
				}
			}
			bg1.frameFinished();
		}

	}
	
	public static void CharDown1(Sprite mainDownD, Sprite orcL, int prevPos, Animation bg1, Sprite mainLeftD, int skeletonY,Sprite SkeletonDown, int mainHP){
		bg1.removeSprite(mainLeftD);
		bg1.addSprite(mainDownD);
	
		
		for(int i = 550;i>250;i--){

			mainDownD.setPosition(1160,i);

			prevPos = orcL.getXposition();
			orcLeft(prevPos,orcL);


			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			if(i == 300){
				System.out.println("Acquired potion! Your HP went up by +2!");
				mainHP+=2;
				System.out.println("Your HP: "+mainHP);
				System.out.println("Found Teleporter in the chest! Teleporting to the main road!");
			}
			bg1.frameFinished();
		}
		
	}
	
	public static void CharRight1(Sprite mainRightD, Sprite orcL, int prevPos, Animation bg1, Sprite mainLeftD, int skeletonY,Sprite SkeletonDown, int mainHP, int OrcHP){
		bg1.removeSprite(mainLeftD);
		bg1.addSprite(mainRightD);
		
		for(int i = 1160;i<1475;i++){
			mainRightD.setPosition(i,550);
			
			prevPos = orcL.getXposition();
			orcLeft(prevPos,orcL);

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);
			
			System.out.println(mainRightD.getXposition()+" Orc: "+ prevPos);
			
			if(mainRightD.getXposition()==prevPos||mainRightD.getXposition()+1==prevPos||mainRightD.getXposition()-1==prevPos){
				FightOrc(mainHP, OrcHP);
			}
			bg1.frameFinished();
		}
		
	}
	
	public static void CharEvent2(Sprite mainRightD, Sprite orcL, int prevPos, Animation bg1, Sprite mainLeftD, int skeletonY,Sprite SkeletonDown, int mainHP){
		System.out.println("Teleported to the crossroads!");
		
	}
	
	public static void mainCharEvent1(Sprite mainLeftD, Sprite orcL, int prevPos, Animation bg1, Sprite sprite1, int skeletonY,Sprite SkeletonDown){
		bg1.removeSprite(sprite1);
		bg1.addSprite(mainLeftD);
		
		for(int i=1475;i>555;i--){
			mainLeftD.setPosition(i,860);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}
	}

	public static void mainCharEvent2(Sprite mainDownD, Sprite orcL, int prevPos, Animation bg1, Sprite mainLeftD, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainLeftD);
		bg1.addSprite(mainDownD);

		for(int i=860;i>594;i--){
			mainDownD.setPosition(555,i);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();
		}
	}

	public static void mainCharEvent3(Sprite mainRightD, Sprite orcL, int prevPos, Animation bg1, Sprite mainDownD, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainDownD);
		bg1.addSprite(mainRightD);

		for(int i=555;i<874;i++){
			mainRightD.setPosition(i,594);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}

		//SPRITE INTERACTION
		if(mainRightD.getXposition()==873 && mainRightD.getYposition()==594){
			System.out.println("Encountered chest! Obtained Shield!");
		}
	}

	public static void mainCharEvent4(Sprite mainLeftS, Sprite orcL, int prevPos, Animation bg1, Sprite mainRightD, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainRightD);
		bg1.addSprite(mainLeftS);

		for(int i=874;i>555;i--){
			mainLeftS.setPosition(i,594);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}
	}

	public static void mainCharEvent5(Sprite mainUpS, Sprite orcL, int prevPos, Animation bg1, Sprite mainLeftS, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainLeftS);
		bg1.addSprite(mainUpS);

		for(int i=594;i<860;i++){
			mainUpS.setPosition(555,i);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}
	}

	public static void mainCharEvent6(Sprite mainLeftS, Sprite orcL, int prevPos, Animation bg1, Sprite mainUpS, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainUpS);
		bg1.addSprite(mainLeftS);

		for(int i=555;i>290;i--){
			mainLeftS.setPosition(i,860);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}
	}

	public static void mainCharEvent7(Sprite mainDownS, Sprite orcL, int prevPos, Animation bg1, Sprite mainLeftS, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainLeftS);
		bg1.addSprite(mainDownS);

		for(int i=860;i>546;i--){
			mainDownS.setPosition(290,i);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}

	}

	public static void mainCharEvent8(Sprite mainLeftS, Sprite orcL, int prevPos, Animation bg1, Sprite mainDownS, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainDownS);
		bg1.addSprite(mainLeftS);

		for(int i=290;i>126;i--){
			mainLeftS.setPosition(i,546);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}
	}

	public static void mainCharEvent9(Sprite mainUpS, Sprite orcL, int prevPos, Animation bg1, Sprite mainLeftS, int skeletonY, Sprite SkeletonDown){
		bg1.removeSprite(mainLeftS);
		bg1.addSprite(mainUpS);

		for(int i=546;i<1000;i++){
			mainUpS.setPosition(126,i);

			orcLeft(prevPos,orcL);
			if(prevPos==1150){
				prevPos=1475;
			}else{
				prevPos--;
			}

			skeletonY = SkeletonDown.getYposition();
			skeleton(skeletonY, SkeletonDown);

			bg1.frameFinished();

		}
		if(mainUpS.getXposition()==126 && mainUpS.getYposition()==999){
			System.out.println("End");
		}
	}

}
