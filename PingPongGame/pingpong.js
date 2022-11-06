    // all the required declarations
    var paddleHeight = 150;
    var paddleWidth = 30;
    var ballRadius = 25;
    var halfPaddleHeight = paddleHeight/2;
    var speedOfPaddle1 = 0;
    var speedOfPaddle2 = 0;
    var positionOfPaddle1 = 220;
    var positionOfPaddle2 = 220;
    var topPositionOfBall = 510;
    var leftPositionOfBall = 820;
    var topSpeedOfBall = 10;
    var leftSpeedOfBall = 0;
    var score1 = 0;
    var score2 = 0;
    var spaceBar = false;
    var space = document.getElementById('spaceBar');
    var space1 = document.getElementById('spaceBar1');
    var net = document.getElementById('net');
    // initially net is hidden 
    net.style.visibility = "hidden";
    var loaded = false;
    // alert message onload
    if (loaded === false){
        window.alert("Rules : First Player to reach 5 points wins the game \r\n Tip: Use Headphones for better Experience");
        loaded = true;
    }
    //space bar pressed and other things start
    window.addEventListener('keydown' , function(event){

        if (event.keyCode == 32){
            spaceBar = true;
            space.style.visibility = "hidden";
            space1.style.visibility = "hidden";
            net.style.visibility = "visible"
        }
    })
    // function to move ball randomly
    function startBall(){
        topPositionOfBall = 510;
        leftPositionOfBall = 820;

        if (Math.random() < 0.5){
            var side = 1;
        }
        else {
            var side = -1;
        }

        leftSpeedOfBall = side * (Math.random() * 6 + 5);
        topSpeedOfBall = Math.random() * 6 + 5;
    }
    //2 Players keys arrow keys for player 3 and ws keys for player 1
    document.addEventListener('keydown' , function(event){
        //up
        if (event.key == 'ArrowUp'){
            speedOfPaddle1 = -10;
            speedOfPaddle2 = -10;
        }
        //down
        if (event.key == 'ArrowDown'){
            speedOfPaddle1 = 10;
            speedOfPaddle2 = 10;
        }
    })

    document.addEventListener('keyup' , function(event){
        //up
        if (event.key == 'ArrowUp'){
            speedOfPaddle1 = 0;
            speedOfPaddle2 = 0;
        }
        //down
        if (event.key == 'ArrowDown'){
            speedOfPaddle1 = 0;
            speedOfPaddle2 = 0;
        }
    })
    // changing the value of paddles and bouncing balls
    window.setInterval(function show(){
        //paddle position
        if (spaceBar == true){
        positionOfPaddle1 += speedOfPaddle1;
        positionOfPaddle2 += speedOfPaddle2;

        topPositionOfBall += topSpeedOfBall;
        leftPositionOfBall += leftSpeedOfBall;
        // paddle does not leave the vw and vh
        if (positionOfPaddle1 <= 1){
            positionOfPaddle1 = 1;
        }
        if (positionOfPaddle2 <= 1){
            positionOfPaddle2 = 1;
        }

        if (positionOfPaddle1 >= window.innerHeight - paddleHeight){
            positionOfPaddle1 = window.innerHeight - paddleHeight;
        }
        if (positionOfPaddle2 >= window.innerHeight - paddleHeight){
            positionOfPaddle2 = window.innerHeight - paddleHeight;
        }
        // bouncing the ball front top and bottom
        if (topPositionOfBall <= 10 || topPositionOfBall >= window.innerHeight - ballRadius){
            topSpeedOfBall = -topSpeedOfBall;
            var audio = new Audio('bounce.mp3')
            audio.play();
        }
        // bouncing the ball from the left paddle
        if (leftPositionOfBall <= paddleWidth){
            if (topPositionOfBall > positionOfPaddle1 && topPositionOfBall < positionOfPaddle1 + paddleHeight){
                leftSpeedOfBall = -leftSpeedOfBall;
                var audio = new Audio('pingpong.mp3')
                audio.play();
            }
            else{
                score2++;
                var audio = new Audio('missedshot.mp3')
                audio.play();
                if (score2 == 5){
                    var snd = new Audio('applause.wav');    
                    snd .onended = function () { alert("Player 1 Wins !!!"); };
                    snd .play();
                    score2 = 0;
                    score1 = 0;
                    spaceBar = false;
                }
                startBall();
            }
        }
        // bouncing the ball fromt the right paddle
        if (leftPositionOfBall >= window.innerWidth - ballRadius - paddleWidth){
            if (topPositionOfBall > positionOfPaddle2 && topPositionOfBall < positionOfPaddle2 + paddleHeight){
                leftSpeedOfBall = -leftSpeedOfBall;
                var audio = new Audio('pingpong.mp3');
                audio.play();
            }
            else {
                score1++;
                var audio = new Audio('missedshot.mp3');
                audio.play();
                if (score1 == 5){
                    var snd = new Audio('applause.wav');    
                    snd .onended = function () { alert("Player 2 Wins !!!"); };
                    snd .play();
                    score1 = 0;
                    score2 = 0;
                    spaceBar = false;
                }
                startBall();
            }
        }
    }
        //getting all the necessary required elements
        document.getElementById('paddle1').style.top = positionOfPaddle1 + 'px';
        document.getElementById('paddle2').style.top = positionOfPaddle2 + 'px';

        document.getElementById('ball').style.top = topPositionOfBall + 'px';
        document.getElementById('ball').style.left = leftPositionOfBall + 'px';

        document.getElementById('score1').innerHTML = score1.toString();
        document.getElementById('score2').innerHTML = score2.toString();

    } , 1000/60);

