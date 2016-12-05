X = load('CSVfile.txt');
figure
hold on
Xheight = size(X,1);

for k = 1:Xheight
    plot(X(k,2:2:end),X(k,3:2:end),'*')
    drawnow
    pause(0.1)
end

%%   E5.6.1
figure 
hold on

Xheight = size(X,1);

maxvalx = ones(Xheight,1);
minvalx = ones(Xheight,1);
maxvaly = ones(Xheight,1);
minvaly = ones(Xheight,1);

time = ones(Xheight,1);

for k = 1:Xheight
    maxvalx(k) = max(X(k,2:2:end));
    minvalx(k) = min(X(k,2:2:end));
    
    maxvaly(k) = max(X(k,3:2:end));
    minvaly(k) = min(X(k,3:2:end)); 
    
    time(k) = X(k,1);
end

v = [maxvalx minvalx maxvaly minvaly];
for elem = v
    plot(time,elem,'-*')
end
legend('maxvalx','minvalx','maxvaly','minvaly')

%%   E5.6.2
figure 
hold on

medelvarde = ones(Xheight,1);
standardavv = ones(Xheight,1);

time = ones(Xheight,1);

for k = 1:Xheight
    medelvarde(k) = mean(X(k,2:2:end));
    standardavv(k) = std(X(k,3:2:end)); 
    
    time(k) = X(k,1);
end

plot(time,medelvarde,'-*')
plot(time,standardavv,'-*')
legend('medelvärde','standardavvikelse')


