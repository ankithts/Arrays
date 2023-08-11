ds<-read.csv("C:/Users/ADMIN/Desktop/GDP.csv",TRUE)

names(ds)<-c("Year","GDP","CO2_emission")
head(ds)
summary(ds)
# boxplot(ds$GDP)
boxplot(ds$CO2_emission)


# hist(ds$CO2_emission)
# hist(ds$GDP)

shapiro.test(ds$GDP)
# shapiro.test(ds$CO2_emission)




