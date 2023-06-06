<br/>
<br/>
<footer class="main-footer" ng-controller="contentCtrl">
    <div class="container">
        <div class="row">
            <div class="col">
                <div>
                    <div class="footer-circle-item">
                        <i class="fa fa-map-pin"></i>
                    </div>
                    <div class="footer-circle-item-title" ng-bind-html="getContent('address')"></div>
                </div>
                <div>
                    <div class="footer-circle-item">
                        <i class="fa fa-mobile"></i>
                    </div>
                    <a href="tel:{{getContent('mobile') | removeHTMLTags}}" class="footer-circle-item-title" ng-bind-html="getContent('mobile')"></a>
                </div>
                <div>
                    <div class="footer-circle-item">
                        <i class="fa fa-envelope"></i>
                    </div>
                    <a href="mailto:{{getContent('email') | removeHTMLTags}}" class="footer-circle-item-title" ng-bind-html="getContent('email')"></a>
                </div>
            </div>
            <div class="col">
                <H4>About the Company</H4>
                <br/>
                <p class="footer-description" ng-bind-html="getContent('footer_about')"></p>
                <br/>
                <div>
                    <a href="{{getContent('facebook_link') | removeHTMLTags}}" class="footer-social">
                        <i class="fa fa-link"></i>
                    </a>
                    <a href="{{getContent('twitter_link') | removeHTMLTags}}" class="footer-social">
                        <i class="fa fa-link"></i>
                    </a>
                    <a href="{{getContent('linkedin_link') | removeHTMLTags}}" class="footer-social">
                        <i class="fa fa-link"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</footer>