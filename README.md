#Used tech stack : Spring Boot ( Framework ) , Java 8 

#External Libraries imported : Google Json and Jackson, Junit, TestNG, PowerMock

#Steps to use :

1. Download project.
2. Open Eclipse and Import Downloaded maven project.
3. Run /Bidding-System/src/main/java/com/bidding/system/Bidding/System/BiddingSystemApplication.java file. This will load initial data.
4. Use PostMan to test below API with following details

APIs:

==== API to place bid ========
HTTP call : POST
URL : http://localhost:8080/auction/100/bid
Parameters : 

bidAmount = <amount to be entered>
userName = <Name of user who is placing the bid>

output : 

{
    "status": true,
    "message": "Bid accepted successfully for item",
    "result": null,
    "code": 201
}

if Bid will get placed successfully then value of status, message and code will change accordingly.
After placement of bid, real time Auction dsahborad will get printed in console, Which will show all running auctions details ( user who placed the bid and bidding 
amount ) as well as rejected bids details if any.

=== API to fetch running bids ====

HTTP call : GET
URL : http://localhost:8080/auction?status=Running&from=0&size=5

where from and size is for pagination purpose.

output : 

{
    "status": true,
    "message": "All Running bids for all items fetched successfully",
    "result": [
        {
            "itemCode": 100,
            "highestBidAmount": 0,
            "stepRate": 0
        },
        {
            "itemCode": 101,
            "highestBidAmount": 0,
            "stepRate": 0
        }
    ],
    "code": 200
}

5. To run test cases , testNG should be added in eclipse . To do the same: 
- Go to Help -> Install New Software -> click on add -> fill details : Name = testNg and Location = http://dl.bintray.com/testng-team/testng-eclipse-release/.
- Select TestNg and Finish.

6. Go to Test classes and click run as TestNg.


