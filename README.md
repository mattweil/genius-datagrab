<h1>Genius Data Grabber</h1>

genius-datagrab is a command line project written in Java that allows you to pull large amounts of data from Genius using the Genius API.

<h2>How to use</h2>

genius-datagrab is fueled by Genius' API which requires an authorization token that can be obtained [here](http://genius.com/api-clients).

| Command | Description | Usage
| --- | --- | ---
| `artist` | Displays artist related information | artist -a [artist]
| `lyrics` | Displays lyrics of entered song | lyrics -s [song] -a [artist]
| `help` | Displays a list of commands and their usage | help [command] 
| `songs` | Displays a list of all songs associated with a particular artist | songs -a [artist]
