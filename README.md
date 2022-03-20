# Auction House

- CLI implementation of an auction house, in which a chosen number of clients are able to bid for a certain item.
- The functionality of the auction house is based on the Producer-Consumer problem.
- The core OOP concepts(abstraction, encapsulation, inheritance, and polymorphism) are applied, and the following design patterns are used: Builder, Strategy, Adapter and Facade.

## Problem description

An auction house wants to implement an "online" auction service. For this app, there are 3 types of users:

- Buyers: these are normal users that can view different products and request that an auction be started for their desired item.
- Administrators: these users can add products to the database.
- Brokers: these users act as intermediaries between the buyers and the auction house.

### Restrictions

- For an auction to start, there have to be at least two buyers present.
- Two auctions can not take place at the same time.
- A client can participate in as many auctions as he wishes.
- The client communicates with the auction house ONLY when initiating an auction. All the other interactions are done through its corresponding broker.

### Key Features

- Products that are listed on the auction house website are visible in the form of a list:
  - Buyers can see this list and preview different products.
  - Brokers can modify this list by deleting the items that have been sold.
  - Administrators can add new items to the list, when there is an empty slot available.
- Clients that want to bid for a product can do so by sending a request to the auction house. The auction house then associates a broker to the client and this broker then continues to manage the whole auction process.
- Brokers get a commission for each associated client, based on some algorithms. These algorithms depend on the client's type and bidding history.

### The bidding process

- A client sends a request to the auction house for a desired product, identified based on its id.
- The auction house then associates a broker to the client.
- When the number of participants has been met, the auction house starts the auction process and notifies all the participating brokers.
- An auction ends after a maximum number of steps. The product is sold to the client who has offered the highest amount. If the highest amount offer does not cross a certain threshold, then the product is not sold.
- At each step of the bidding process, the brokers ask the clients for their offer, information which they forward to the auction house. The auction house calculates the highest offer at each step and then informs the brokers, which, in turn, give this information to the buyers.
- When the number of maximum steps has been reached, the clients are notified about the results, and the broker-client communication is stopped.

### Implementation details

- Details regarding the implementation of this program can be found in the README inside the "Proiect" folder.
- As the program has been fully written in Romanian (packages, class names, variables, Javadoc), that README is also in the same language.
- A full translation of this program in English might come sometime in the future.
