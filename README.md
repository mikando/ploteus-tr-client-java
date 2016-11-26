## Java Client for Ploteus Turkey Integration Pool

##$ Example usage of uploading learning opportunities:

```java
String learningOpportunitiesXml = "....";
PloteusTRClient  clientInstance = new PloteusTRClient("USER_NAME", "PASSWORD");
String result = clientInstance.uploadLearningOpportunitiesXml(learningOpportunitiesXml);
```
