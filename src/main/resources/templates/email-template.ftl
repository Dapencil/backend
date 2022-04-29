<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<style>
    #tickets {
        width: 250px;
        position: relative;
        margin: 100px auto;
    }
    #tickets:after {
        content: '';
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 8px;
        box-shadow: 4px 0 80px -30px #000;
        border-radius: 10px;
    }
    .ticket-container {
        position: relative;
        overflow: hidden;
    }
    .ticket {
        width: 237px;
        height: 60px;
        background: #fff;
        border: 3px solid #e8ebf0;
        border-right: 0;
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
    }
    .corner,
    .corners {
        width: 18px;
        position: absolute;
        top: 0;
        bottom: 0;
    }
    .corner {
        height: 26px;
        right: -8px;
        margin: auto;
        z-index: 2;
        border: 3px solid #e8ebf0;
        border-radius: 7px;
        box-sizing: border-box;
    }
    .corners {
        right: -11px;
        z-index: 1;
    }
    .corners:before,
    .corners:after {
        content: '';
        display: block;
        width: 10px;
        height: 23px;
        position: absolute;
        left: -3px;
        background: #fff;
        box-sizing: border-box;
    }
    .corners:before {
        top: 0;
        border-top: 3px solid #e8ebf0;
        border-right: 3px solid #e8ebf0;
        border-top-right-radius: 10px;
    }
    .corners:after {
        bottom: 0;
        border-bottom: 3px solid #e8ebf0;
        border-right: 3px solid #e8ebf0;
        border-bottom-right-radius: 10px;
    }
</style>
<body>
<div class="container">

    <div class="ticket basic">
        <p>Admit One</p>
    </div>

    <div class="ticket airline">
        <div class="top">
            <h1>boarding pass</h1>
            <div class="big">
                <p class="from">${from}</p>
                <p class="to"><i class="fas fa-arrow-right"></i>${too}</p>
            </div>
            <div class="top--side">
                <i class="fas fa-plane"></i>
                <p>Baltimore</p>
                <p>San Diego</p>
            </div>
        </div>
        <div class="bottom">
            <div class="column">
                <div class="row row-1">
                    <p><span>Flight</span>AA2005</p>
                    <p class="row--right"><span>Gate</span>B3</p>
                </div>
                <div class="row row-2">
                    <p class="row--center"><span>Departs</span>11:00 AM</p>
                    <p class="row--right"><span>Arrives</span>1:05 PM</p>
                </div>
                <div class="row row-3">
                    <p><span>Passenger</span>Jesus Ramirez</p>
                    <p class="row--center"><span>Seat</span>11E</p>
                    <p class="row--right"><span>Group</span>3</p>
                </div>
            </div>
            <div class="bar--code"></div>
        </div>
    </div>
</div>
</body>
</html>