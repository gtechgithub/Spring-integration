This is an example of spring integration feed inbound channel adapter where it reads
RSS feeds from specified URL, and out Junit receives or read  or gets from feeds channel
Feed polled will be sent to the channel specified in channel.

a) 
<int-feed:inbound-channel-adapter id="feedInBoundChannelAdapter" 
                                  channel="feedChannel"  auto-startup="true" 
                                  url="http://feeds.bbci.co.uk/news/video_and_audio/world/rss.xml?edition=uk">
	<int:poller fixed-rate="10000" max-messages-per-poll="100" />
</int-feed:inbound-channel-adapter>	

b) payload is of type he payload will be of type com.sun.syndication.feed.syn.SyndEntryImpl hence
we need to convert accordingly, also A poller element is required for
the feed adapter, because it is a poller consumer.


                  @Autowired
	                PollableChannel feedChannel;


                  Message message = (Message) feedChannel.receive(10000);
	                if (message != null){
	                    SyndEntryImpl entry = (SyndEntryImpl) message.getPayload();
	                    System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
	                }

