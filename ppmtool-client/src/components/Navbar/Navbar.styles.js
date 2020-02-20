import styled from 'styled-components';

export const StyledLink = styled.span`
    a {
        padding: 0.5rem 0.5rem;
        color: rgba(255, 255, 255, 0.5);
        text-decoration: none;
        /* text-transform: uppercase; */
        letter-spacing: 0.5px;
        /* font-weight: 600; */
    }

    a.active {
        color: ${props => props.theme.colors.white};
    }

    a:hover {
        color: ${props => props.theme.colors.white};
    }
`;
